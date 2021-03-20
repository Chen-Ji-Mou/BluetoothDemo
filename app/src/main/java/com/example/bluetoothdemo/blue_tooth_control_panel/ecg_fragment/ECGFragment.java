package com.example.bluetoothdemo.blue_tooth_control_panel.ecg_fragment;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bluetoothdemo.ECGChart;
import com.example.bluetoothdemo.ECGUtil;
import com.example.bluetoothdemo.R;
import com.example.bluetoothdemo.blue_tooth_control_panel.BluetoothControlPanelActivity;

import java.io.InputStream;
import java.util.Arrays;

public class ECGFragment extends Fragment {

    private ECGChart ecgChart;
    private TextView heartRate;
    private TextView temperature;
    private TextView steps;
    private TextView distance;
    private TextView ecgValue;
    private ECGUtil ecgUtil = new ECGUtil();
    private View view;
    private BluetoothSocket socket;
    private InputStream inputStream;

    private static final String TAG = "ECGFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_ecg, container,false);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent != null){
            parent.removeView(view);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ecgChart = view.findViewById(R.id.ECGChart_ECGFragment);
        heartRate = view.findViewById(R.id.HeartRate_ECGFragment);
        temperature = view.findViewById(R.id.Temperature_ECGFragment);
        steps = view.findViewById(R.id.Steps_ECGFragment);
        distance = view.findViewById(R.id.Distance_ECGFragment);
        ecgValue = view.findViewById(R.id.ECGValue_ECGFragment);

        if (isAdded()){
            socket = ((BluetoothControlPanelActivity)requireActivity()).getSocket();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    do {
                        try {
                            inputStream = socket.getInputStream();
                            if (inputStream != null){
                                final StringBuilder data = new StringBuilder();
                                char character;
                                while ((character = (char)inputStream.read()) != '$'){
                                    data.append(character);
                                }
//                                Log.d(TAG, "6666  "+ data.toString());
//                                Log.d(TAG, "6666  "+data.length());
                                if (data.length() < 12){
                                    continue;
                                }
                                if (!data.toString().equals("")){
                                    requireActivity().runOnUiThread(new Runnable() {
                                        @SuppressLint("SetTextI18n")
                                        @Override
                                        public void run() {
                                            String[] datas = data.toString().split(" ");
//                                            float temperature_value = Integer.parseInt(datas[0]);
                                            if (datas.length < 5){
                                                return;
                                            }
                                            temperature.setText("Temperature："+Integer.parseInt(datas[0])+"℃");
                                            ecgValue.setText("ECGValue："+Integer.parseInt(datas[1]));
                                            ecgUtil.showWaveData(ecgChart, Integer.parseInt(datas[1]));
                                            heartRate.setText("HeartRate："+Integer.parseInt(datas[2]));
                                            steps.setText("Steps："+Integer.parseInt(datas[3]));
                                            distance.setText("Distance："+Integer.parseInt(datas[4]));
                                        }
                                    });
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } while (ECGFragment.this.isVisible());
                }
            }).start();
        }
    }
}
