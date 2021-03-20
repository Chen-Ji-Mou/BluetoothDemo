package com.example.bluetoothdemo.blue_tooth_control_panel.chat_fragment;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluetoothdemo.R;
import com.example.bluetoothdemo.custom_class.ChatModel;
import com.example.bluetoothdemo.custom_class.ItemModel;
import com.example.bluetoothdemo.main_activity.MainActivity_RecyclerViewAdapter;

import java.util.List;

public class ChatFragment_RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemModel> list;

    ChatFragment_RecyclerViewAdapter(Context context, List<ItemModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position){
        return list.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder = null;
        switch(viewType){
            case ItemModel.REMOTE_DEVICE:
                holder = new ChatLeftViewHolder(layoutInflater.inflate(R.layout.chat_fragment_list_item_left, parent, false));
                break;
            case ItemModel.LOCAL_DEVICE:
                holder = new ChatRightViewHolder(layoutInflater.inflate(R.layout.chat_fragment_list_item_right, parent, false));
                break;
        }
        assert holder != null;
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemModel itemModel = list.get(position);
        if (itemModel.getType() == ItemModel.LOCAL_DEVICE){
            ChatRightViewHolder viewHolder = (ChatRightViewHolder) holder;
            viewHolder.textContent.setText(((ChatModel)itemModel.getObject()).getChat_content());
        }else if (itemModel.getType() == ItemModel.REMOTE_DEVICE){
            ChatLeftViewHolder viewHolder = (ChatLeftViewHolder) holder;
            viewHolder.textContent.setText(((ChatModel)itemModel.getObject()).getChat_content());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ChatLeftViewHolder extends RecyclerView.ViewHolder{

        private TextView textContent;

        ChatLeftViewHolder(@NonNull View itemView) {
            super(itemView);
            textContent = itemView.findViewById(R.id.textContent_ChatFragmentListItemLeft);
        }
    }

    public static class ChatRightViewHolder extends RecyclerView.ViewHolder{

        private TextView textContent;

        ChatRightViewHolder(@NonNull View itemView) {
            super(itemView);
            textContent = itemView.findViewById(R.id.textContent_ChatFragmentListItemRight);
        }
    }
}
