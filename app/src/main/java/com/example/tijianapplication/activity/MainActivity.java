package com.example.tijianapplication.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.MotionEvent;
import android.widget.RadioGroup;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.tijianapplication.R;
import com.example.tijianapplication.utils.StatusBarUtils;
import com.parttime.base.base.BaseActivity;
import com.parttime.base.constants.RoutMap;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.util.PermissionUtil;
import com.parttime.base.util.UserInfoUtil;

import butterknife.BindView;


@Route(path = RoutMap.ACTIVITY_MAIN)
public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";


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


//    @Override
//    protected void initSavedInstance(Bundle savedInstanceState) {
//        if (savedInstanceState!=null){
//            currentId = savedInstanceState.getInt(BUNDLE_CACHE_INDEX_KEY,-1);
//            //如果已经添加过fragment, 需要隐藏操作
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction transaction = fragmentManager.beginTransaction();
//            homeFragment = (HomeFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG+R.id.rb_home);
//            recommendFragment = (RecommendFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG+R.id.rb_recommend);
//            allFragment = (HotFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG+R.id.rb_all);
//            mineFragment = (MineFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG+R.id.rb_mine);
//            if (homeFragment!=null){
//                transaction.hide(homeFragment);
//            }
//            if (recommendFragment!=null){
//                transaction.hide(recommendFragment);
//            }
//            if (allFragment!=null){
//                transaction.hide(allFragment);
//            }
//            if (mineFragment!=null){
//                transaction.hide(mineFragment);
//            }
//            transaction.commit();
//            if (currentId!=-1){
//                rg_menu.check(currentId);
//            }else {
//                rg_menu.check(R.id.rb_home);
//            }
//        }
//
//    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);

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
