package com.example.tijianapplication.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tijianapplication.R;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.rx.RxBus;
import com.parttime.base.rx.RxEvent;


/**
 *
 */
public class ColorTestViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem> {

    View view;
    public ColorTestViewHolder(View itemView) {
        super(itemView);
        view = itemView.findViewById(R.id.color_view);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        ColorBean colorBean = (ColorBean) recyclerItem.data;
        view.setBackgroundColor(Color.parseColor(colorBean.getColor()));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (colorBean.isTrue()){
                    RxBus.getInstance().post(new RxEvent(1,null));
                }
            }
        });

    }
}