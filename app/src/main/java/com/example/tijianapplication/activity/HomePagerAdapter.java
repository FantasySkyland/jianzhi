package com.example.tijianapplication.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.tijianapplication.fragment.SlbFragment;

import java.util.ArrayList;

/**
 * Created by zdy On 2024/5/14.
 */
public class HomePagerAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"视力表","视力测试","色盲测试","散光测试","颜色敏感度测试"};
    private ArrayList<Fragment> fragments;
    public HomePagerAdapter(@NonNull FragmentManager fm , ArrayList<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
