package com.example.tijianapplication.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tijianapplication.R;
import com.example.tijianapplication.utils.StatusBarUtils;
import com.example.tijianapplication.dialog.ResultDialog;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.rx.RxEvent;

import butterknife.BindView;
import butterknife.OnClick;

public class CalculatorActivity extends BaseActivity {

    @BindView(R.id.bt_js)
    Button bt_js;
    @BindView(R.id.et_nl)
    EditText et_nl;
    @BindView(R.id.et_sg)
    EditText et_sg;
    @BindView(R.id.et_tz)
    EditText et_tz;
    @BindView(R.id.rg_xb)
    RadioGroup rg_xb;
    @BindView(R.id.rg_yd)
    RadioGroup rg_yd;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    private ResultDialog resultDialog;

    public static void start(Context context){
        Intent intent = new Intent(context, CalculatorActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_calcutor;
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
        tv_desc.setText("This calculation formula is suitable for people with normal body shape. If you are a \"muscle type\", the calorie requirement will be underestimated, and if you are an \"obese type\", the calorie requirement will be overestimated. 1 kg of fat requires about 3600 calories. Through \"healthy diet + more exercise\", you can reduce calories. We recommend that you " +
                "learn more about obesity and weight loss, lose weight healthily, and maintain an ideal healthy weight.");
        bt_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_nl.length()==0 || et_tz.length()==0|| et_sg.length()==0){
                    return;
                }
                int nl = Integer.parseInt(et_nl.getText().toString());
                int tz = Integer.parseInt(et_tz.getText().toString());
                int sg = Integer.parseInt(et_sg.getText().toString());
                int male = 1;
                if(rg_xb.getCheckedRadioButtonId() == R.id.rb_man){
                    male = 1;
                }else {
                    male = 2;
                }
                int level = 1;
                if(rg_yd.getCheckedRadioButtonId() == R.id.rb_yd_1){
                    level = 1;
                }else if(rg_yd.getCheckedRadioButtonId() == R.id.rb_yd_2){
                    level = 2;
                } else if(rg_yd.getCheckedRadioButtonId() == R.id.rb_yd_3){
                    level = 3;
                }else if(rg_yd.getCheckedRadioButtonId() == R.id.rb_yd_4){
                    level = 4;
                }else if(rg_yd.getCheckedRadioButtonId() == R.id.rb_yd_5){
                    level = 5;
                }else if(rg_yd.getCheckedRadioButtonId() == R.id.rb_yd_6){
                    level = 6;
                }
                resultDialog.setCalculatorResult(nl,sg,tz,male,level);
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
