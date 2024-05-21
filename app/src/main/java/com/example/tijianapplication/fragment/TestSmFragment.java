package com.example.tijianapplication.fragment;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.tijianapplication.R;
import com.example.tijianapplication.activity.TestResultActivity;
import com.google.android.material.button.MaterialButton;
import com.parttime.base.base.BaseFragment;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.util.ToastUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author : sklyand
 * @email : zhengdengyao
 * @time :
 * @describe ：
 */
public class TestSmFragment extends BaseFragment {
    MaterialButton mButtonView;
    MaterialButton mButtonView2;
    MaterialButton mButtonView3;
    ImageView circleImageView;
    TextView text;
    private int correctCount;
    private int currentCount;
    private HashMap<Integer,Integer> hashMap = new HashMap();
    @Override
    protected int getContentLayoutId() {
        return R.layout.test_sm_fragment;
    }

    @Override
    protected void initWidget(View root) {
        mButtonView=root.findViewById(R.id.radiobutton1);
        mButtonView2=root.findViewById(R.id.radiobutton2);
        mButtonView3=root.findViewById(R.id.radiobutton3);
        circleImageView=root.findViewById(R.id.circleImageView);
        text=root.findViewById(R.id.text);
    }

    @Override
    protected void initData() {
        hashMap.put(0,0);
        hashMap.put(1,0);
        hashMap.put(2,1);
        hashMap.put(3,1);
        hashMap.put(4,0);
        mButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(0);
            }
        });
        mButtonView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(1);
            }
        });
        mButtonView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next(2);
            }
        });
    }
    private void next(int flag){
        int result  = hashMap.get(currentCount);
        if (flag == result){
            correctCount++;
        }
        currentCount++;
        if (currentCount==0){
            correctCount = 0;
            mButtonView.setText("6");
            mButtonView2.setText("8");
            circleImageView.setImageResource(R.drawable.sm1);
            text.setText("1,Please select the number you see");
        }else if (currentCount==1){
            mButtonView.setText("26");
            mButtonView2.setText("28");
            circleImageView.setImageResource(R.drawable.sm2);
            text.setText("2,Please select the number you see");
        }else if (currentCount==2){
            mButtonView.setText("duck");
            mButtonView2.setText("swan");
            circleImageView.setImageResource(R.drawable.sm3);
            text.setText("3,Please select the animal you saw");
        }else if (currentCount==3){
            mButtonView.setText("26");
            mButtonView2.setText("25");
            circleImageView.setImageResource(R.drawable.sm4);
            text.setText("4,Please select the number you see");
        }else if (currentCount==4){
            mButtonView.setText("goldfish");
            mButtonView2.setText("tiger");
            circleImageView.setImageResource(R.drawable.sm5);
            text.setText("5,Please select the animal you saw");
        }else {
            TestResultActivity.start(getContext(),2,  correctCount );
            currentCount = 0;
            correctCount = 0;
            mButtonView.setText("6");
            mButtonView2.setText("8");
            circleImageView.setImageResource(R.drawable.sm1);
            text.setText("1,Please select the number you see");
        }
    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }
}
