package com.example.tijianapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tijianapplication.R;
import com.example.tijianapplication.utils.DecimalDigitsInputFilter;
import com.example.tijianapplication.utils.StatusBarUtils;
import com.example.tijianapplication.dialog.ResultDialog;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.rx.RxEvent;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.OnClick;

public class BodyIndexActivity extends BaseActivity {

    @BindView(R.id.bt_js)
    Button bt_js;
    @BindView(R.id.et_sg)
    EditText et_sg;
    @BindView(R.id.et_tz)
    EditText et_tz;
    @BindView(R.id.tv_desc_1)
    TextView tv_desc_1;
    private ResultDialog resultDialog;

    public static void start(Context context){
        Intent intent = new Intent(context, BodyIndexActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_body_index;
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
        et_tz.setFilters(DecimalDigitsInputFilter.getFilters(new DecimalDigitsInputFilter(3,2)));
        et_sg.setFilters(DecimalDigitsInputFilter.getFilters(new DecimalDigitsInputFilter(3,2)));
        bt_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_sg.length()==0 || et_tz.length()==0){
                    return;
                }
                Double sg = Double.parseDouble(et_sg.getText().toString());
                Double tz = Double.parseDouble(et_tz.getText().toString());
                BigDecimal bd = new BigDecimal(Double.toString(tz/(sg*sg)*10000));
                bd = bd.setScale(2, RoundingMode.HALF_UP); // 使用默认的四舍五入策略
                resultDialog.setResult(bd.doubleValue());
                resultDialog.show();
            }
        });
        tv_desc_1.setText("1.Weight status is divided into:\n·····BMI<18.5 Too light\n·····18.5<BMI<24 Moderate" +
                "\n·····24<BMI<28 Heavy\n·····28<BMI<34 Obesity\n·····BMI>34 Very obese");
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
