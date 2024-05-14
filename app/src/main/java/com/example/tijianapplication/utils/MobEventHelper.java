package com.example.tijianapplication.utils;

import android.content.Context;
import android.util.Log;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by zdy On 2019/12/4.
 */
public class MobEventHelper {
    /**
     * 友盟统计
     */
    public static void statistics(Context context, String key, String event) {
        Map<String, Object> eventMap = new HashMap<>();
        eventMap.put("event",event);

        Log.d("友盟统计","key:"+key+"map");
    }
}
