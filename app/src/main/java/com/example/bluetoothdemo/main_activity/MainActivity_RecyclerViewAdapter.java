package com.example.bluetoothdemo.main_activity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluetoothdemo.blue_tooth_control_panel.BluetoothControlPanelActivity;
import com.example.bluetoothdemo.R;

import java.util.List;

public class MainActivity_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<BluetoothDevice> list;
    private int itemViewType;
    private final int
            NO_DATA = 0, //无数据
            NORMAL_VIEW = 1; //正常布局

    MainActivity_RecyclerViewAdapter(Context context, List<BluetoothDevice> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position){
        if(list.size() <= 0){
            return NO_DATA;//无数据处理
        }
        return NORMAL_VIEW;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        switch(viewType){
            case NO_DATA:
                holder = new ViewHolder(layoutInflater.inflate(R.layout.item_no_data, parent,false));
                break;
            case NORMAL_VIEW:
                holder = new ViewHolder(layoutInflater.inflate(R.layout.main_activity_list_item, parent,false));
                break;
        }
        assert holder != null;
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if(list.size() <= 0){
            return;//无数据的情况
        }
        if (itemViewType == NORMAL_VIEW) {
            BluetoothDevice bluetoothDevice = list.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;

            if (bluetoothDevice.getName() == null){
                viewHolder.deviceName.setText("无");
            }else {
                viewHolder.deviceName.setText(bluetoothDevice.getName());
            }

            viewHolder.deviceHardwareAddress.setText(bluetoothDevice.getAddress());

            if (bluetoothDevice.getBondState() == 10){
                viewHolder.isTheDevicePaired.setTextColor(context.getColor(R.color.red));
                viewHolder.isTheDevicePaired.setText("未配对");
            }else if (bluetoothDevice.getBondState() == 12){
                viewHolder.isTheDevicePaired.setTextColor(context.getColor(R.color.green));
                viewHolder.isTheDevicePaired.setText("已配对");
            }
        }
    }

    @Override
    public int getItemCount() {
        int result;
        if (list.size() > 0){
            result = list.size();
            itemViewType = NORMAL_VIEW;
        }else {
            result = 1;
            itemViewType = NO_DATA;
        }
        return result;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView deviceName;
        private TextView deviceHardwareAddress;
        private TextView isTheDevicePaired;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            if (itemViewType == NORMAL_VIEW){

                ConstraintLayout itemContent = itemView.findViewById(R.id.itemContent_MainActivityListItem);
                deviceName = itemView.findViewById(R.id.deviceName_MainActivityListItem);
                deviceHardwareAddress = itemView.findViewById(R.id.deviceHardwareAddress_MainActivityListItem);
                isTheDevicePaired = itemView.findViewById(R.id.isTheDevicePaired_MainActivityListItem);

                itemContent.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("LongLogTag")
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        BluetoothDevice bluetoothDevice = list.get(position);
                        if (bluetoothDevice.getBondState() == 10){
                            bluetoothDevice.createBond();
                        }else if (bluetoothDevice.getBondState() == 12){
                            Intent intent = new Intent(context, BluetoothControlPanelActivity.class);
                            intent.putExtra("bluetoothDevice", bluetoothDevice);
                            context.startActivity(intent);
                        }
                    }
                });
            }
        }
    }


}
