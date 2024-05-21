package com.example.tijianapplication.fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tijianapplication.R;
import com.example.tijianapplication.activity.TestResultActivity;
import com.example.tijianapplication.adapter.ColorBean;
import com.example.tijianapplication.adapter.CommonAdapter;
import com.google.android.material.button.MaterialButton;
import com.parttime.base.base.BaseFragment;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.rx.RxEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author : sklyand
 * @email : zhengdengyao
 * @time :
 * @describe ：
 */
public class TestColorFragment extends BaseFragment {

    String[] colors = new String[]{"#3F8130","#9C274A","#02E3E4","#D42300","#8D00B8","#FAF523","#31CA82","#082AB5","#F87D01","#032FF9"};
    String[] correctColors = new String[]{"#539543","#93314B","#74F7FD","#EF0E27","#AB00E0","#FCFF00","#3BEC99","#032FF9","#FC8903","#FF95EF"};

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CommonAdapter commonAdapter;
    private Random random;
    private int count;
    private int spanCount = 2;
    private int level = 0;
    private TextView tv_level;
    private TextView tv_count_down_title;
    private TextView tv_count_down;
    private Button bt_start;
    private ImageView iv_start;

    @Override
    protected int getContentLayoutId() {
        return R.layout.test_color_fragment;
    }

    @Override
    protected void initWidget(View root) {
        recyclerView = root.findViewById(R.id.recycler);
        tv_level = root.findViewById(R.id.tv_level);
        tv_count_down_title = root.findViewById(R.id.tv_count_down_title);
        tv_count_down = root.findViewById(R.id.tv_count_down);
        bt_start = root.findViewById(R.id.bt_start);
        iv_start = root.findViewById(R.id.iv_start);
        gridLayoutManager = new GridLayoutManager(getContext(),spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        commonAdapter = new CommonAdapter();
        recyclerView.setAdapter(commonAdapter);
        random = new Random();
        refreshData();
        CountDownTimer countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                tv_count_down.setText(String.format("%d",l/1000));
            }

            @Override
            public void onFinish() {
                iv_start.setVisibility(View.VISIBLE);
                TestResultActivity.start(getContext(),4,level);
                clear();
            }
        };
        iv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_start.setVisibility(View.GONE);
                countDownTimer.start();
            }
        });
    }

    private void clear(){
        spanCount = 2;
        level = 0;
        refreshData();
    }

    @Override
    protected void initData() {

    }


    @Override
    public void handleDefaultEvent(RxEvent event) {
        if (event.getEventType()==1){
            level++;
           refreshData();
        }
    }

    private void refreshData(){
        tv_level.setText(String.format("level:%d",level+1));
        int colorIndex;
        colorIndex  = level%9;
        spanCount = 2+level/3;
        count = spanCount*spanCount;
        gridLayoutManager.setSpanCount(spanCount);
        int real = random.nextInt(count);
        ArrayList<BaseRecyclerAdapter.RecyclerItem> items = new ArrayList<>();
        for (int  i = 0;i<count;i++){
            BaseRecyclerAdapter.RecyclerItem recyclerItem = new BaseRecyclerAdapter.RecyclerItem();
            recyclerItem.type = CommonAdapter.COLOR;
            ColorBean colorBean = new ColorBean();
            if (i == real){
                colorBean.setColor(correctColors[colorIndex]);
                colorBean.setTrue(true);
            }else {
                colorBean.setColor(colors[colorIndex]);
                colorBean.setTrue(false);
            }
            recyclerItem.data = colorBean;
            items.add(recyclerItem);
        }
        commonAdapter.replace(items);
    }
}
