package com.parttime.base.util;

import android.annotation.SuppressLint;

import com.parttime.base.base.App;
import com.parttime.base.retrofit.ApiService;
import com.parttime.base.retrofit.RetrofitServiceCreator;
import com.parttime.base.rx.RxBus;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.rx.RxThrowableConsumer;
import com.parttime.base.rx.RxUtils;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * @author : sklyand
 * @email :
 * @time : 2019/7/25 11:44
 * @describe ：
 */
public class UserInfoUtil {
    private volatile static UserInfoUtil instance;
    private UserInfoUtil(){}

    public static UserInfoUtil getInstance(){
        if (instance == null){
            synchronized (UserInfoUtil.class){
                if (instance == null){
                    instance = new UserInfoUtil();
                }
            }
        }
        return instance;
    }

    public  void setUserId(String userId){
        SpUtil.putString(App.getInstant(),"userId",userId);
    }

    public  String getUserId(){
        return SpUtil.getString(App.getInstant(),"userId","-1");
    }




//    /**
//     * 上传个人信息
//     * @param imgStr   头像base64
//     * @param sex 性别
//     * @param birthday 生日
//     * @param exp 工作经验
//     * @param des 自我介绍
//     * 除了头像其他不能为空
//     */
//    public void upDateUser(String imgStr,int sex,String birthday,String exp,String des,String realName){
//        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
//        HashMap<String, String> data = new HashMap<>();
//        data.put("imgStr",imgStr);
//        data.put("sex",String.valueOf(sex));
//        data.put("birthday",birthday);
//        data.put("exp",exp);
//        data.put("des",des);
//        data.put("realName",realName);
//        apiService.updateUser(data)
//                .compose(RxUtils.rxSchedulerHelper())
//                .subscribe(jobCommonBean -> {
//                    ToastUtils.showShortToast("信息保存成功");
//                    UserInfoUtil.getInstance().upDateLocalUser();
//                },new RxThrowableConsumer());
//    }
//
//    /**
//     * @param nickName 昵称
//     */
//    @SuppressLint("CheckResult")
//    public void upDateNiceName(String nickName){
//        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
//        HashMap<String ,String> data = new HashMap<>();
//        data.put("nickName",nickName);
//        apiService.updateNickName(data)
//                .compose(RxUtils.rxSchedulerHelper())
//                .subscribe(jobCommonBean -> {
//                    UserInfoUtil.getInstance().upDateLocalUser();
//                    ToastUtils.showShortToast("修改昵称成功");
//                },new RxThrowableConsumer(){
//                    @Override
//                    public void handleThrowable(Throwable throwable) {
//                        super.handleThrowable(throwable);
//                        ToastUtils.showShortToast("修改昵称失败");
//                    }
//                });
//    }
}
