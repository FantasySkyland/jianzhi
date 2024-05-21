package com.example.tijianapplication.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.tijianapplication.R;
import com.example.tijianapplication.activity.TestResultActivity;
import com.example.tijianapplication.utils.DimenUtil;
import com.example.tijianapplication.view.ShiLiEView;
import com.example.tijianapplication.view.ShiLiEView2;
import com.parttime.base.base.BaseFragment;
import com.parttime.base.rx.RxEvent;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author : sklyand
 * @email : zhengdengyao
 * @time :
 * @describe ：
 */
public class TestEyesFragment extends BaseFragment {

    static final int DIRECT_UNCLEAR = -1;
    static final int DIRECT_UP = 0;
    static final int DIRECT_RIGHT = 1;
    static final int DIRECT_DOWN = 2;
    static final int DIRECT_LEFT = 3;
    private TextView tv_left;
    private TextView tv_right;
    private TextView tv_top;
    private TextView tv_bottom;
    private TextView tv_center;
    private int mIndex = 0;
    private float mLastToDegrees = 0;
    private int mDirect = 1;
    private int mRet = 0;
    private int currentCount;
    private int currentCorrect;
    private int currentError;
    private int lastResult;//1 正确 2 错误  0刚开始
    private int currentResult;//1 正确 2 错误  0还没有结果
    private double shili = 4.0;
    private TextView tv_shili;
    private float from = 1.0f;
    private float to = 1.0f;
    private ShiLiEView2 shi_e_view;
    private int level = 9;
    private LinearLayout result_icon;

    @Override
    protected int getContentLayoutId() {
        return R.layout.test_eyes_fragment;
    }

    @Override
    protected void initWidget(View root) {
        tv_left = root.findViewById(R.id.tv_left);
        tv_right = root.findViewById(R.id.tv_right);
        tv_top = root.findViewById(R.id.tv_top);
        tv_bottom = root.findViewById(R.id.tv_bottom);
        tv_center = root.findViewById(R.id.tv_center);
        tv_shili = root.findViewById(R.id.tv_shili);
        shi_e_view = root.findViewById(R.id.shi_e_view);
        result_icon = root.findViewById(R.id.result_icon);
        initView();

    }

    private void initView(){
        shi_e_view.refreshELevel(9);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                left();
            }
        });
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                right();
            }
        });
        tv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                up();
            }
        });

        tv_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                down();
            }
        });
        tv_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unClear();
            }
        });
    }
    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {


    }

    private void up() {
        if (checkError(DIRECT_UP)){
            currentCorrect++;
            addResultIcon(false);
        }else {
            currentError++;
            addResultIcon(true);
        }
        next();
    }

    private void down() {
        if (checkError(DIRECT_DOWN)){
            currentCorrect++;
            addResultIcon(false);
        }else {
            currentError++;
            addResultIcon(true);
        }
        next();

    }

    private void left() {
        if (checkError(DIRECT_LEFT)){
            currentCorrect++;
            addResultIcon(false);
        }else {
            currentError++;
            addResultIcon(true);
        }
        next();

    }

    private void right() {

        if (checkError(DIRECT_RIGHT)){
            currentCorrect++;
            addResultIcon(false);
        }else {
            currentError++;
            addResultIcon(true);
        }
        next();

    }

    private void addResultIcon(boolean error){
        ImageView imageView = new ImageView(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DimenUtil.dipToPixels(20),DimenUtil.dipToPixels(20));
        imageView.setLayoutParams(layoutParams);
        if (error){
            imageView.setImageResource(R.mipmap.error_icon);
        }else {
            imageView.setImageResource(R.mipmap.correct_icon);

        }
        result_icon.addView(imageView,0);
    }

    /**
     * 看不清楚 两次后终止
     */
    private void unClear() {
        //已经错误一次 再次错误
        currentError++;
        addResultIcon(true);
        next();
    }

    /**
     * 检查当前是否错误
     *
     * @return flag
     */
    private boolean checkError(int flag) {
        return mDirect == flag;
    }



    private void next() {
        currentCount++;
        if (currentCount == 3 ){
            if (currentCorrect ==3){
                currentResult = 1;
            }else if (currentError == 3){
                currentResult = 2;
            }else {
                currentResult = 0;
            }
        }
        if (currentCount > 3 ){
            if (currentCorrect ==4){
                currentResult = 1;
            }else if (currentError == 4){
                currentResult = 2;
            }else {
                currentResult = 0;
            }
        }
        if (currentResult == 0){
            playAnim(0);
        }else if (lastResult == 0 || lastResult == currentResult){
            if (currentResult == 1){
                shili  = shili+0.1;
                level++;
                playAnim(1);
            }else {
                shili  =shili- 0.1;
                level--;
                playAnim(2);
            }
            result_icon.removeAllViews();
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            String formatSl = decimalFormat.format(shili);
            tv_shili.setText(formatSl);
            lastResult = currentResult;
            currentResult = 0;
            currentError = 0;
            currentCorrect = 0;
            currentCount = 0;
            if (mIndex>=8){
                showResult();
            }
        } else {
            showResult();
        }

    }

    private void showResult() {
        TestResultActivity.start(getContext(),1,level);
        level=9;
        shi_e_view.refreshELevel(9);
        currentResult = 0;
        lastResult = 0;
        currentError = 0;
        currentCorrect = 0;
        currentCount = 0;
        shili = 4.0;
        result_icon.removeAllViews();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formatSl = decimalFormat.format(shili);
        tv_shili.setText(formatSl);
//        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
//        dialog.setTitle("测试结果");
//        String msg = "";
//        dialog.setMessage(msg);
//        dialog.create();
////        dialog.setNegativeButton("重新测试", new DialogInterface.OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////
////                    finish();
////
////                    startActivity(new Intent(TestEyeActivity2.this, TestEyeActivity.class));
////
////
////            }
////        });
//
//        dialog.setPositiveButton("获取报告", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//               // startActivity(new Intent(TestEyeActivity2.this, ReportActivity2.class));
//
//            }
//        });
//        dialog.show();

    }

    /**
     * 播放动画 缩放 + 旋转
     * 随着mIndex++ 图片每次缩小10%
     * mDirect方向随机生成 如果错误一次不缩小 只换旋转
     */
    private void playAnim(int type) {
        ScaleAnimation scaleAnimation;
        if (type == 1){
            from = to;
            to = to - (float) (0.2);
            mIndex++;
        }else if (type == 2){
            from = to;
            to = to + (float) (0.2);
            mIndex++;
        }
        shi_e_view.refreshELevel(level);
//        AnimationSet set = new AnimationSet(true);
//        if (type != 0){
//            scaleAnimation = new ScaleAnimation(from, to, from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//
//        }else {
//            scaleAnimation = new ScaleAnimation(to, to, from, to, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        }
        //set.addAnimation(scaleAnimation);

        Random random = new Random();
        int rd = random.nextInt(4);

        //保存本次旋转角度 以便下次使用
        float fromDegrees = mLastToDegrees;
        mLastToDegrees = fromDegrees + 90 * rd;
        float toDegrees = mLastToDegrees;

        shi_e_view.setImageBitmap(rotateImage(toDegrees));
//        RotateAnimation rotate = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        set.addAnimation(rotate);
//
//        set.setDuration(0);
//        set.setFillAfter(true);
//
        //shi_e_view.startAnimation(set);

        //记录旋转后的方向
        mDirect += rd;
        if (mDirect < 4) {
            mDirect += 4;
        }
        mDirect %= 4;

    }


    public Bitmap rotateImage(float angle) {
        Bitmap source = BitmapFactory.decodeResource(getContext().getResources(),R.mipmap.img_e);
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

}
