package com.example.tijianapplication.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tijianapplication.R;
import com.example.tijianapplication.dialog.TimeDialog;
import com.example.tijianapplication.utils.DateUtil;
import com.example.tijianapplication.utils.StatusBarUtils;
import com.example.tijianapplication.dialog.ResultDialog;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.util.ToastUtils;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.OnClick;

public class SmokeActivity extends BaseActivity {

    @BindView(R.id.bt_js)
    Button bt_js;
    @BindView(R.id.et_smoke_times)
    EditText et_smoke_times;
    @BindView(R.id.bt_start_time)
    Button bt_start_time;
    @BindView(R.id.bt_end_time)
    Button bt_end_time;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    int type = 1;//1 start 2 end
    private long startTime = 0;
    private long endTime = 0;
    private ResultDialog resultDialog;
    private DatePickerDialog datePickerDialog;
    private DateTime dateTime;

    public static void start(Context context){
        Intent intent = new Intent(context, SmokeActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_smoke;
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
        dateTime = new DateTime();
        datePickerDialog = new DatePickerDialog(this);
       datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
           @Override
           public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
               dateTime =  dateTime.withDate(i,i1+1,i2);
               if (type == 1){
                   startTime = dateTime.getMillis();
                   tv_start_time.setText(DateUtil.getDateToString(dateTime.getMillis()));
               }else {
                   endTime = dateTime.getMillis();
                   tv_end_time.setText(DateUtil.getDateToString(dateTime.getMillis()));
               }
               datePickerDialog.dismiss();
           }
       });
        resultDialog = new ResultDialog(this);
        resultDialog.setClickListener(new ResultDialog.ClickListener() {
            @Override
            public void onClick() {
                resultDialog.dismiss();
            }
        });
        bt_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 1;
                datePickerDialog.show();
            }
        });
        bt_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = 2;
                datePickerDialog.show();
            }
        });
        bt_js.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( et_smoke_times.length()==0){
                    return;
                }
                if (startTime==0){
                    Toast toast = new Toast(SmokeActivity.this);
                    toast.setText("请选择开始时间");
                    return;
                }
                if (endTime==0){
                    Toast toast = new Toast(SmokeActivity.this);
                    toast.setText("请选择结束时间");
                    return;
                }
                if (endTime<startTime){
                    Toast toast = new Toast(SmokeActivity.this);
                    toast.setText("结束时间要大于开始时间");
                    return;
                }
                int days = (int) ((endTime - startTime)/(3600*1000*24));
                if (days<1){
                    days = 1;
                }
                int smoke_times = Integer.parseInt(et_smoke_times.getText().toString());
                int count = days*smoke_times;
                double ddd = count*13/(60*24.0);
                BigDecimal bd = new BigDecimal(Double.toString(ddd));
                bd = bd.setScale(1, RoundingMode.HALF_UP); // 使用默认的四舍五入策略
                resultDialog.setSmokeResult(count,bd.toString());
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
