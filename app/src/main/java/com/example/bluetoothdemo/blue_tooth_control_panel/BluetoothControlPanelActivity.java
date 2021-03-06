package com.example.bluetoothdemo.blue_tooth_control_panel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bluetoothdemo.R;
import com.example.bluetoothdemo.SetSystemBar;
import com.example.bluetoothdemo.blue_tooth_control_panel.chat_fragment.ChatFragment;
import com.example.bluetoothdemo.blue_tooth_control_panel.ecg_fragment.ECGFragment;
import com.example.bluetoothdemo.blue_tooth_control_panel.keyboard_fragment.KeyboardFragment;
import com.example.bluetoothdemo.dialog.LoadAnimationDialog;
import com.google.android.material.tabs.TabLayout;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class BluetoothControlPanelActivity extends AppCompatActivity {

    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket socket;
    private BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    private LoadAnimationDialog loadAnimationDialog;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_control_panel);
        SetSystemBar.setStatusBarColor(this, R.color.blue);
        SetSystemBar.setAndroidNativeLightStatusBar(this, false);

        Toolbar toolbar = findViewById(R.id.toolbar_BluetoothControlPanelActivity);
        viewPager = findViewById(R.id.viewPager_BluetoothControlPanelActivity);
        tabLayout = findViewById(R.id.tabLayout_BluetoothControlPanelActivity);

        toolbar.setTitle("????????????");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        loadAnimationDialog = LoadAnimationDialog.showDialog(this,"???????????????????????????...");

        bluetoothDevice = getIntent().getParcelableExtra("bluetoothDevice");
        loadAnimationDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //????????????????????????????????????????????????
                bluetoothAdapter.cancelDiscovery();
                try {
                    //??????socket????????????????????????????????????????????????????????????????????????
                    socket = bluetoothDevice.createRfcommSocketToServiceRecord(bluetoothDevice.getUuids()[0].getUuid());
                    socket.connect();
                } catch (Exception e) {
                    try {
                        socket.close();
                        loadAnimationDialog.cancel();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(BluetoothControlPanelActivity.this,"???????????????????????????",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    } catch (Exception ee) {
                        ee.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadAnimationDialog.cancel();
                        Toast.makeText(BluetoothControlPanelActivity.this,"???????????????",Toast.LENGTH_SHORT).show();
                        List<Fragment> fragments = new ArrayList<>();
                        fragments.add(new ChatFragment());
                        fragments.add(new KeyboardFragment());
                        fragments.add(new ECGFragment());
                        tabLayout.setTabTextColors(getResources().getColor(R.color.dark_grey),getResources().getColor(R.color.blue));
                        tabLayout.setupWithViewPager(viewPager);
                        BluetoothControlPanelActivity_ViewPagerAdapter viewPagerAdapter = new BluetoothControlPanelActivity_ViewPagerAdapter(getSupportFragmentManager(), fragments);
                        viewPager.setAdapter(viewPagerAdapter);
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public BluetoothSocket getSocket() {
        return socket;
    }

    @Override
    protected void onDestroy() {
        try {
            socket.close();
//            InputStream inputStream = socket.getInputStream();
//            inputStream.close();
//            OutputStream outputStream = socket.getOutputStream();
//            outputStream.close();
            Toast.makeText(BluetoothControlPanelActivity.this,"????????????????????????",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
