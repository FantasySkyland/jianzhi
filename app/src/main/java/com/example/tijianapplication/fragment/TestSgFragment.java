package com.example.tijianapplication.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tijianapplication.R;
import com.example.tijianapplication.activity.TestResultActivity;
import com.google.android.material.button.MaterialButton;
import com.parttime.base.base.BaseFragment;
import com.parttime.base.rx.RxEvent;

import java.util.HashMap;

/**
 * @author : sklyand
 * @email : zhengdengyao
 * @time :
 * @describe ：
 */
public class TestSgFragment extends BaseFragment {
    MaterialButton mButtonView;
    MaterialButton mButtonView2;

    private HashMap<Integer,Integer> hashMap = new HashMap();
    @Override
    protected int getContentLayoutId() {
        return R.layout.test_sg_fragment;
    }

    @Override
    protected void initWidget(View root) {
        mButtonView=root.findViewById(R.id.buttonView6);
        mButtonView2=root.findViewById(R.id.buttonView7);
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
                TestResultActivity.start(getContext(),3,1);
            }
        });
        mButtonView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TestResultActivity.start(getContext(),3,2);
            }
        });

    }


    @Override
    public void handleDefaultEvent(RxEvent event) {

    }
}
