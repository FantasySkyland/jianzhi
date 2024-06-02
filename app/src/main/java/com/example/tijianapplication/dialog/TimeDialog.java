package com.example.tijianapplication.dialog;

import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.tijianapplication.R;
import com.parttime.base.dialog.BaseDialog;

import org.joda.time.DateTime;

import butterknife.BindView;

/**
 * Created by zdy On 2020/1/6.
 */
public class TimeDialog extends BaseDialog {
    @BindView(R.id.tv_confirm)
    TextView tv_confirm;
    @BindView(R.id.date_picker)
    DatePicker date_picker;
    private ClickListener clickListener;
    private DateTime dateTime;

    public ClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener{
        void  onClick(long millis);
    }
    public TimeDialog(Context context) {
        super(context);
    }
    @Override
    protected int setContentLayout() {
        return R.layout.dialog_choose_time;
    }



    @Override
    protected void initView(View root) {
        dateTime = DateTime.now();
        date_picker.init(dateTime.getYear(), dateTime.getMonthOfYear()-1, dateTime.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                dateTime.withDate(i,i1,i2);
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (clickListener!=null){
                   clickListener.onClick(dateTime.getMillis());
               }
            }
        });
    }
}
