package com.parttime.base.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * @Author zhengdengyao
 * @Created 2018/12/27 0027
 * @Description
 */
public  class FocusDialog extends Dialog{
    public FocusDialog(@NonNull Context context) {
        super(context);
    }

    public FocusDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FocusDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private FocusChangeListener focusChangeListener;

    public FocusChangeListener getFocusChangeListener() {
        return focusChangeListener;
    }

    public void setFocusChangeListener(FocusChangeListener focusChangeListener) {
        this.focusChangeListener = focusChangeListener;
    }

    public interface FocusChangeListener{
        void onWindowFocusChanged(boolean hasFocus);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (focusChangeListener!=null){
            focusChangeListener.onWindowFocusChanged(hasFocus);
        }
    }
}
