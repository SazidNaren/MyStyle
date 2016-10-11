package com.ar.mystyle.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ar.mystyle.fragment.ImageFragment;
import java.util.ArrayList;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    ArrayList<String> arrayList;
    public MyPagerAdapter(ArrayList<String> arrayList,FragmentManager fm) {
        super(fm);
        this.arrayList=arrayList;
    }

    @Override
    public Fragment getItem(int pos) {
        Bundle bundle=new Bundle();
        bundle.putString("url",arrayList.get(pos));
        Fragment fragment=new ImageFragment();
        fragment.setArguments(bundle);
            return fragment;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}