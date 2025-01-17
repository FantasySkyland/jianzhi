package com.parttime.base.base;

import android.os.Bundle;


import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.parttime.base.R;
import com.parttime.base.constants.RoutMap;
import com.parttime.base.rx.RxBus;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.rx.RxUtils;

import butterknife.ButterKnife;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/18 15:39
 * @describe ：
 */
public abstract class BaseActivity extends AActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 在界面未初始化之前调用的初始化窗口
        if (savedInstanceState!=null){
            ARouter.getInstance().build(RoutMap.ACTIVITY_SPLASH).navigation();
            return;
        }
        initWindows();
        if (initArgs(getIntent().getExtras())) {
            // 得到界面Id并设置到Activity界面中
            int layId = getContentLayoutId();
            setContentView(layId);
            ButterKnife.bind(this);
            initWidget();
            initData();
            registerDefaultEvent();
        } else {
            finish();
        }
    }

    /**
     * 初始化相关参数
     * @param bundle 参数Bundle
     * @return 如果参数正确返回True，错误返回False
     */
    public boolean initArgs(Bundle bundle){
        return true;
    }


    /**
     * 初始化窗口
     */
    protected void initWindows(){
        if (!leftToRightBack()){
            setTheme(R.style.NoSwipeToDismissTheme);
        }
    }
    /**
     * 得到当前界面的资源文件id
     * @return 资源文件id
     */
    protected  boolean leftToRightBack(){
        return false;
    }
    /**
     *
     * @return 是否左滑退出
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected  abstract void initWidget();

    /**
     * 初始化数据
     */
    protected  abstract void initData();

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
    /**
     * 注册rxbus订阅事件
     */
    public void registerDefaultEvent() {
        RxBus.getInstance().toFlowable(RxEvent.class).compose(RxUtils.rxSchedulerHelper2())
                .compose(mProvider.bindToLifecycle())
                .subscribe(event -> handleDefaultEvent(event));
    }


    /**
     * 处理默认订阅事件
     *
     * @param event
     */
    public abstract void handleDefaultEvent(RxEvent event);
//    @Override
//    public void onBackPressed() {
//        // 得到当前Activity下的所有Fragment
//        List<Fragment> fragments = getSupportFragmentManager().getFragments();
//        for (android.support.v4.app.Fragment fragment:fragments){
//            // 判断是否为我们能够处理的Fragment类型
//            if (fragment instanceof Fragment){
//                // 判断是否拦截了返回按钮
//                if (((Fragment) fragment).onBackPressed()){
//                    // 如果有直接Return
//                    return;
//                }
//            }
//        }
//        super.onBackPressed();
//        finish();
//    }
}
