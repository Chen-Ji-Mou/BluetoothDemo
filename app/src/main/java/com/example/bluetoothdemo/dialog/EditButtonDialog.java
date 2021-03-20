package com.example.bluetoothdemo.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.bluetoothdemo.R;
import com.example.bluetoothdemo.blue_tooth_control_panel.BluetoothControlPanelActivity;

import java.io.InputStream;
import java.util.Objects;

public class EditButtonDialog extends DialogFragment {

    private TextView buttonText;
    private TextView buttonMessage;
    private int position;

    public EditButtonDialog(int position) {
        this.position = position;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_button_dialog,null);
        builder.setView(view)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getTargetFragment() != null){
                            Intent intent = new Intent();
                            getTargetFragment().onActivityResult(position, Activity.RESULT_CANCELED, intent);
                            EditButtonDialog.this.dismiss();
                        }
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(getTargetFragment() != null){
                            Intent intent = new Intent();
                            intent.putExtra("button_text",buttonText.getText().toString());
                            intent.putExtra("button_message",buttonMessage.getText().toString());
                            getTargetFragment().onActivityResult(position, Activity.RESULT_OK, intent);
                            EditButtonDialog.this.dismiss();
                        }
                    }
                });
        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        buttonText = view.findViewById(R.id.buttonText_EditButtonDialog);
        buttonMessage = view.findViewById(R.id.buttonMessage_EditButtonDialog);
        return dialog;
    }
}
