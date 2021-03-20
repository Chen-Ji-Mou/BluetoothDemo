package com.example.bluetoothdemo.blue_tooth_control_panel.chat_fragment;

import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluetoothdemo.R;
import com.example.bluetoothdemo.blue_tooth_control_panel.BluetoothControlPanelActivity;
import com.example.bluetoothdemo.blue_tooth_control_panel.ecg_fragment.ECGFragment;
import com.example.bluetoothdemo.custom_class.ChatModel;
import com.example.bluetoothdemo.custom_class.ItemModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatFragment extends Fragment {

    private EditText inputBox;
    private View view;
    private Button sendView;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private ChatFragment_RecyclerViewAdapter recyclerViewAdapter;
    private List<ItemModel> list = new ArrayList<>();
    private boolean have_content_inputBox = false;

    private static final String TAG = "ChatFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_chat, container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent != null){
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_ChatFragment);
        inputBox = view.findViewById(R.id.inputBox_ChatFragment);
        sendView = view.findViewById(R.id.sendButton_ChatFragment);

        if (isAdded()){
            socket = ((BluetoothControlPanelActivity)requireActivity()).getSocket();
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //添加item的添加和移除动画, 这里使用系统默认的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter = new ChatFragment_RecyclerViewAdapter(getActivity(), list);
        recyclerView.setAdapter(recyclerViewAdapter);

        inputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!TextUtils.isEmpty(s)){
                    have_content_inputBox = true;
                }else{
                    have_content_inputBox = false;
                }
                if(have_content_inputBox){
                    sendView.setEnabled(true);
                }else{
                    sendView.setEnabled(false);
                }
            }
        });

        inputBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                outputStream = socket.getOutputStream();
                                if (outputStream != null){
                                    byte[] bytes = new byte[inputBox.getText().length()];
                                    for (int i = 0; i < inputBox.getText().length(); i++) {
                                        char character = inputBox.getText().charAt(i);
                                        bytes[i] = (byte) character;
                                    }
                                    outputStream.write(bytes);
                                    list.add(new ItemModel(ItemModel.LOCAL_DEVICE,new ChatModel(inputBox.getText().toString())));
                                    if (isAdded())
                                        requireActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                recyclerViewAdapter.notifyDataSetChanged();
                                                inputBox.setText("");

                                            }
                                        });
                                }
                            }catch (Exception e){
                                if (isAdded())
                                    requireActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(requireActivity(),"发送失败！",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            }
                        }
                    }).start();
                }
                return false;
            }
        });

        sendView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                byte[] bytes = new byte[inputBox.getText().length()];
                                for (int i = 0; i < inputBox.getText().length(); i++) {
                                    char character = inputBox.getText().charAt(i);
                                    bytes[i] = (byte) character;
                                }
                                outputStream.write(bytes);
                                list.add(new ItemModel(ItemModel.LOCAL_DEVICE,new ChatModel(inputBox.getText().toString())));
                                if (isAdded())
                                    requireActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            recyclerViewAdapter.notifyDataSetChanged();
                                            inputBox.setText("");

                                        }
                                    });
                            }
                        }catch (Exception e){
                            if (isAdded())
                                requireActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(requireActivity(),"发送失败！",Toast.LENGTH_SHORT).show();
                                    }
                                });
                        }
                    }
                }).start();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    try {
                        inputStream = socket.getInputStream();
                        if (inputStream != null){
                            StringBuilder value_read = new StringBuilder();
                            Thread.sleep(200);
                            while (inputStream.available() != 0){
                                value_read.append((char)inputStream.read());
                            }
                            if (!value_read.toString().equals("")){
                                list.add(new ItemModel(ItemModel.REMOTE_DEVICE, new ChatModel(value_read.toString())));
                                if (isAdded())
                                    requireActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            recyclerViewAdapter.notifyDataSetChanged();
                                        }
                                    });
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } while (ChatFragment.this.isVisible());
            }
        }).start();
    }
}
