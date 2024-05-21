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

    private String[] titles = new String[]{"Eye chart","vision test","color blindness test","Astigmatism test","Color sensitivity test"};
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
