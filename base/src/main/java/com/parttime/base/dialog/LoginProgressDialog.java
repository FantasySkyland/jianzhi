package com.parttime.base.dialog;

import android.app.Dialog;
import android.content.Context;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.parttime.base.R;


/**
 * 登录使用的对话框
 */
public class LoginProgressDialog extends Dialog {


    TextView tvLoadDesc;

    public LoginProgressDialog(@NonNull Context context) {
        this(context, R.style.BaseDialog);
    }

    public LoginProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initDialog();
    }



    private void initDialog() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        tvLoadDesc = contentView.findViewById(R.id.tv_load_desc);
        setContentView(contentView);
        getWindow().setGravity(Gravity.CENTER);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void show(String text) {
        tvLoadDesc.setText(text);
        show();
    }
}
