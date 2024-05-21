package com.example.tijianapplication.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.tijianapplication.R;
import com.parttime.base.base.BaseFragment;

import com.parttime.base.rx.RxEvent;
import com.parttime.base.util.SpUtil;
import com.parttime.base.util.UserInfoUtil;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class SlbFragment extends BaseFragment {


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initWidget(View root) {
        initView();

    }

    private void initView(){

    }
    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {


    }


}
