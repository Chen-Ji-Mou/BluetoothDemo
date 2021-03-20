package com.example.bluetoothdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bluetoothdemo.R;

public class LoadAnimationDialog extends Dialog {

    private LoadAnimationDialog(@NonNull Context context) {
        super(context);
    }

    public static LoadAnimationDialog showDialog(Context context, String text){
        LoadAnimationDialog dialog = new LoadAnimationDialog(context);
        dialog.setContentView(R.layout.load_animation_dialog);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        TextView textView = dialog.findViewById(R.id.text_LoadAnimationDialog);
        textView.setText(text);
        return dialog;
    }
}
