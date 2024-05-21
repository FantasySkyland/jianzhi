package com.example.tijianapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.tijianapplication.R;
import com.example.tijianapplication.utils.StatusBarUtils;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.rx.RxEvent;


import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {
    public static void start(Context context){
        Intent intent = new Intent(context, AboutUsActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_about_us;
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
