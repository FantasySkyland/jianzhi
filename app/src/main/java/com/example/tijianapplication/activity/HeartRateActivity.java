package com.example.tijianapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tijianapplication.R;
import com.example.tijianapplication.utils.StatusBarUtils;
import com.example.tijianapplication.dialog.ResultDialog;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.rx.RxEvent;

import butterknife.BindView;
import butterknife.OnClick;

public class HeartRateActivity extends BaseActivity {

    @BindView(R.id.bt_js)
    Button bt_js;
    @BindView(R.id.et_nl)
    EditText et_nl;
    @BindView(R.id.et_xl)
    EditText et_xl;
    private ResultDialog resultDialog;

    public static void start(Context context){
        Intent intent = new Intent(context, HeartRateActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_heart_rate;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        resultDialog = new ResultDialog(this);
        resultDialog.setClickListener(new ResultDialog.ClickListener() {
            @Override
            public void onClick() {
                resultDialog.dismiss();
            }
        });
        bt_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_nl.length()==0 || et_xl.length()==0){
                    return;
                }
                int nl = Integer.parseInt(et_nl.getText().toString());
                int xl = Integer.parseInt(et_xl.getText().toString());
                resultDialog.setRateResult(nl,xl);
                resultDialog.show();
            }
        });
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Override
    public void handleDefaultEvent(RxEvent event) {

    }
}
