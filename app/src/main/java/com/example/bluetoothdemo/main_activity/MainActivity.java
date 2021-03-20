package com.example.bluetoothdemo.main_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bluetoothdemo.R;
import com.example.bluetoothdemo.SetSystemBar;
import com.example.bluetoothdemo.dialog.LoadAnimationDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private MainActivity_RecyclerViewAdapter recyclerViewAdapter;
    private BluetoothAdapter bluetoothAdapter;
    private LoadAnimationDialog loadAnimationDialog;
    private List<BluetoothDevice> list = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetSystemBar.setStatusBarColor(this, R.color.blue);
        SetSystemBar.setAndroidNativeLightStatusBar(this, false);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_MainActivity);
        Toolbar toolbar = findViewById(R.id.toolbar_MainActivity);

        toolbar.setTitle("蓝牙连接助手");
        setSupportActionBar(toolbar);

        loadAnimationDialog = LoadAnimationDialog.showDialog(this,"查找设备中，请稍后...");

        //获取设备自身的蓝牙适配器对象
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加item的添加和移除动画, 这里使用系统默认的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerViewAdapter = new MainActivity_RecyclerViewAdapter(this, list);
        recyclerView.setAdapter(recyclerViewAdapter);

        //当发现设备时注册 ACTION_FOUND 广播接收器
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(blue_tooth_receiver, filter);

        //开始查找设备
        //loadAnimationDialog.show();
        list.clear();
        if (bluetoothAdapter == null){
            //loadAnimationDialog.cancel();
            Toast.makeText(MainActivity.this,"该设备无法使用蓝牙功能！", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            if (!bluetoothAdapter.isEnabled()) {
                //loadAnimationDialog.cancel();
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent,1);
            }else {
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                if (pairedDevices.size() > 0) {
                    list.addAll(pairedDevices);
                    recyclerViewAdapter.notifyDataSetChanged();
                    bluetoothAdapter.startDiscovery();
                }else {
                    bluetoothAdapter.startDiscovery();
                }
            }
        }
    }

    private final BroadcastReceiver blue_tooth_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null){
                switch (action) {
                    case BluetoothDevice.ACTION_FOUND:
                        // 发现已找到设备。从Intent获取BluetoothDevice对象及其信息
                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        if (device != null) {
                            if (list.indexOf(device) == -1) {
                                list.add(device);
                                recyclerViewAdapter.notifyDataSetChanged();
                            }
                        }
                        break;
                    case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                        Toast.makeText(MainActivity.this, "查找设备完成，点击列表中的设备来尝试连接！", Toast.LENGTH_SHORT).show();
                        //loadAnimationDialog.cancel();
                        break;
                    case BluetoothDevice.ACTION_BOND_STATE_CHANGED:
                        BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                        if (bluetoothDevice != null){
                            switch (bluetoothDevice.getBondState()) {
                                case BluetoothDevice.BOND_BONDED://配对成功
                                    Toast.makeText(MainActivity.this, "配对成功，请点击进行连接！", Toast.LENGTH_SHORT).show();
                                    recyclerViewAdapter.notifyDataSetChanged();
                                    break;
                                case BluetoothDevice.BOND_BONDING://配对中
                                    break;
                                case BluetoothDevice.BOND_NONE://配对失败
                                    break;
                            }
                        }
                        break;
                    case BluetoothAdapter.ACTION_STATE_CHANGED:
                        int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                        if (blueState == BluetoothAdapter.STATE_OFF) {
                            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(enableBtIntent, 1);
                        }
                        break;
                }
            }
        }
    };

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if (resultCode == -1){
                //loadAnimationDialog.show();
                list.clear();
                Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
                if (pairedDevices.size() > 0) {
                    list.addAll(pairedDevices);
                    recyclerViewAdapter.notifyDataSetChanged();
                    bluetoothAdapter.startDiscovery();
                }else {
                    bluetoothAdapter.startDiscovery();
                }
            }else{
                Toast.makeText(MainActivity.this,"该设备未开启蓝牙！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_turn_on_bluetooth) {
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent,1);
            }else {
                Toast.makeText(MainActivity.this,"该设备已开启蓝牙！", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销 ACTION_FOUND 广播接收器
        unregisterReceiver(blue_tooth_receiver);
    }
}
