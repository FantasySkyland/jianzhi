package com.example.tijianapplication.activity;


import android.content.Context;
import android.content.Intent;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


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


@Route(path = RoutMap.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<Fragment> fragments = new ArrayList<>();

    TabLayout tab;
    ViewPager viewpager;
    private Fragment currentFragment;

    //缓存当前Tab的选中下标
    public static final String BUNDLE_CACHE_INDEX_KEY = "bundle_cache_index_key";
    //fragment缓存标签
    public static final String FRAGMENT_TAG = "fragment_tag";
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
        viewpager = findViewById(R.id.viewpager);
        tab = findViewById(R.id.tab);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout_main);
        ImageView iv_nav = findViewById(R.id.iv_nav);
        TextView tv_setting = findViewById(R.id.tv_setting);
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
        fragments.add(new SlbFragment());
        fragments.add(new TestEyesFragment());
        fragments.add(new TestSmFragment());
        fragments.add(new TestSgFragment());
        fragments.add(new TestColorFragment());
        HomePagerAdapter fragmentPagerAdapter = new HomePagerAdapter(getSupportFragmentManager(),fragments);
        viewpager.setAdapter(fragmentPagerAdapter);
        tab.setupWithViewPager(viewpager);
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
        if (event.getEventType()==1){

        }
    }



}
