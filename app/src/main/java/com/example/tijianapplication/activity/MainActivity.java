package com.example.tijianapplication.activity;


import android.content.Context;
import android.content.Intent;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tijianapplication.MainActivity2;
import com.example.tijianapplication.R;
import com.example.tijianapplication.fragment.SlbFragment;
import com.example.tijianapplication.fragment.TestColorFragment;
import com.example.tijianapplication.fragment.TestEyesFragment;
import com.example.tijianapplication.fragment.TestSgFragment;
import com.example.tijianapplication.fragment.TestSmFragment;
import com.example.tijianapplication.utils.StatusBarUtils;
import com.google.android.material.tabs.TabLayout;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.constants.RoutMap;
import com.parttime.base.rx.RxEvent;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


@Route(path = RoutMap.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.rl_rate)
    RelativeLayout rl_rate;
    @BindView(R.id.rl_index)
    RelativeLayout rl_index;
    @BindView(R.id.rl_calorie)
    RelativeLayout rl_calorie;
    @BindView(R.id.rl_smoke)
    RelativeLayout rl_smoke;
    @BindView(R.id.tv_yszc)
    TextView tv_yszc;
    public static void start(Context context){
        Intent intent  = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void initWidget() {
        ButterKnife.bind(this);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_main);
        ImageView iv_nav = findViewById(R.id.iv_nav);
        TextView tv_setting = findViewById(R.id.tv_setting);
        rl_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BodyIndexActivity.start(MainActivity.this);
            }
        });
        rl_calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalculatorActivity.start(MainActivity.this);
            }
        });
        rl_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HeartRateActivity.start(MainActivity.this);
            }
        });
        rl_smoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmokeActivity.start(MainActivity.this);
            }
        });
        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
                AboutUsActivity.start(MainActivity.this);
            }
        });
        iv_nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        StatusBarUtils.statusbar(this);
        tv_yszc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebActivity.start(MainActivity.this,"https://fantasyskyland.github.io/jz.github.io/");
            }
        });
//        PermissionUtil.requestPermissionCombined(this, new PermissionUtil.CombinedPermissionListenerImp() {
//            @Override
//            public void onCombinedGranted() {
//                super.onCombinedGranted();
//            }
//        }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }



}
