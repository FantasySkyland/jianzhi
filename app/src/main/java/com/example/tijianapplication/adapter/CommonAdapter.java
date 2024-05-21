package com.example.tijianapplication.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.example.tijianapplication.R;
import com.parttime.base.base.BaseRecyclerAdapter;


/**
 * @author : sklyand
 * @email :
 * @time : 2020/2/28 11:22
 * @describe ：
 */
public class CommonAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.RecyclerItem> {
    public final static int COLOR = 0;


    @Override
    protected int getItemViewType(int position, RecyclerItem recyclerItem) {
        switch (recyclerItem.type){
            case COLOR:
                return R.layout.item_color_test;
        }

        return R.layout.item_color_test;
    }

    @Override
    protected ViewHolder<RecyclerItem> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        switch (viewType){
            //首页部分
            case  R.layout.item_color_test:
                return new ColorTestViewHolder(root);
        }
        return new ColorTestViewHolder(root);
    }

}
