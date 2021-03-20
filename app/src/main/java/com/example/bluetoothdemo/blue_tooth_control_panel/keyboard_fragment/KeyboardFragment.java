package com.example.bluetoothdemo.blue_tooth_control_panel.keyboard_fragment;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bluetoothdemo.R;
import com.example.bluetoothdemo.blue_tooth_control_panel.BluetoothControlPanelActivity;
import com.example.bluetoothdemo.dialog.EditButtonDialog;

import java.io.IOException;
import java.io.OutputStream;

import static android.content.Context.MODE_PRIVATE;

public class KeyboardFragment extends Fragment {

    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;
    private View view;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_keyboard, container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent != null){
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        button = view.findViewById(R.id.button_KeyboardFragment);
        button2 = view.findViewById(R.id.button2_KeyboardFragment);
        button3 = view.findViewById(R.id.button3_KeyboardFragment);
        button4 = view.findViewById(R.id.button4_KeyboardFragment);
        button5 = view.findViewById(R.id.button5_KeyboardFragment);
        button6 = view.findViewById(R.id.button6_KeyboardFragment);
        button7 = view.findViewById(R.id.button7_KeyboardFragment);
        button8 = view.findViewById(R.id.button8_KeyboardFragment);
        button9 = view.findViewById(R.id.button9_KeyboardFragment);
        button10 = view.findViewById(R.id.button10_KeyboardFragment);
        button11 = view.findViewById(R.id.button11_KeyboardFragment);
        button12 = view.findViewById(R.id.button12_KeyboardFragment);
        button13 = view.findViewById(R.id.button13_KeyboardFragment);
        button14 = view.findViewById(R.id.button14_KeyboardFragment);
        button15 = view.findViewById(R.id.button15_KeyboardFragment);

        if (isAdded()){
            socket = ((BluetoothControlPanelActivity)requireActivity()).getSocket();
            sharedPreferences = requireActivity().getSharedPreferences("data", MODE_PRIVATE);
        }

        button.setText(sharedPreferences.getString("button_text",""));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(1);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,1);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button2.setText(sharedPreferences.getString("button2_text",""));
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button2_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(2);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,2);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button3.setText(sharedPreferences.getString("button3_text",""));
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button3_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(3);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,3);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button4.setText(sharedPreferences.getString("button4_text",""));
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button4_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(4);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,4);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button5.setText(sharedPreferences.getString("button5_text",""));
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button5_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(5);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,5);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button6.setText(sharedPreferences.getString("button6_text",""));
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button6_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(6);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,6);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button7.setText(sharedPreferences.getString("button7_text",""));
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button7_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(7);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,7);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button8.setText(sharedPreferences.getString("button8_text",""));
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button8_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(8);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,8);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button9.setText(sharedPreferences.getString("button9_text",""));
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button9_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button9.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(9);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,9);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button10.setText(sharedPreferences.getString("button10_text",""));
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button10_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(10);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,10);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button11.setText(sharedPreferences.getString("button11_text",""));
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button11_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(11);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,11);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button12.setText(sharedPreferences.getString("button12_text",""));
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button12_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(12);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,12);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button13.setText(sharedPreferences.getString("button13_text",""));
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button13_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button13.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(13);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,13);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button14.setText(sharedPreferences.getString("button14_text",""));
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button14_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button14.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(14);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,14);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });

        button15.setText(sharedPreferences.getString("button15_text",""));
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            outputStream = socket.getOutputStream();
                            if (outputStream != null){
                                char character = sharedPreferences.getString("button15_message","").charAt(0);
                                byte[] bytes = new byte[]{(byte) character};
                                try {
                                    outputStream.write(bytes);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        button15.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                EditButtonDialog editButtonDialog = new EditButtonDialog(15);
                editButtonDialog.setTargetFragment(KeyboardFragment.this,15);
                editButtonDialog.show(getParentFragmentManager(),"editButtonDialog");
                return true;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences.Editor editor;
        switch (requestCode){
            case 1:
                if (resultCode == -1){
                    button.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button_text", data.getStringExtra("button_text"));
                    editor.putString("button_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 2:
                if (resultCode == -1){
                    button2.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button2_text", data.getStringExtra("button_text"));
                    editor.putString("button2_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 3:
                if (resultCode == -1){
                    button3.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button3_text", data.getStringExtra("button_text"));
                    editor.putString("button3_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 4:
                if (resultCode == -1){
                    button4.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button4_text", data.getStringExtra("button_text"));
                    editor.putString("button4_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 5:
                if (resultCode == -1){
                    button5.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button5_text", data.getStringExtra("button_text"));
                    editor.putString("button5_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 6:
                if (resultCode == -1){
                    button6.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button6_text", data.getStringExtra("button_text"));
                    editor.putString("button6_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 7:
                if (resultCode == -1){
                    button7.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button7_text", data.getStringExtra("button_text"));
                    editor.putString("button7_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 8:
                if (resultCode == -1){
                    button8.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button8_text", data.getStringExtra("button_text"));
                    editor.putString("button8_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 9:
                if (resultCode == -1){
                    button9.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button9_text", data.getStringExtra("button_text"));
                    editor.putString("button9_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 10:
                if (resultCode == -1){
                    button10.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button10_text", data.getStringExtra("button_text"));
                    editor.putString("button10_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 11:
                if (resultCode == -1){
                    button11.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button11_text", data.getStringExtra("button_text"));
                    editor.putString("button11_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 12:
                if (resultCode == -1){
                    button12.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button12_text", data.getStringExtra("button_text"));
                    editor.putString("button12_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 13:
                if (resultCode == -1){
                    button13.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button13_text", data.getStringExtra("button_text"));
                    editor.putString("button13_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 14:
                if (resultCode == -1){
                    button14.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button14_text", data.getStringExtra("button_text"));
                    editor.putString("button14_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
            case 15:
                if (resultCode == -1){
                    button15.setText(data.getStringExtra("button_text"));
                    editor = sharedPreferences.edit();
                    editor.putString("button15_text", data.getStringExtra("button_text"));
                    editor.putString("button15_message", data.getStringExtra("button_message"));
                    editor.apply();
                }
                break;
        }
    }
}
