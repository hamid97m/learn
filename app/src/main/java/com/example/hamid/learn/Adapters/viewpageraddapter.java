package com.example.hamid.learn.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hamid.learn.View.firstfragment;
import com.example.hamid.learn.View.fragment2;
import com.example.hamid.learn.View.recycle_fragmenr;

/**
 * Created by Hamid on 7/13/2017.
 */

public class viewpageraddapter extends FragmentPagerAdapter {

    public viewpageraddapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "جدیدترین ها";
            case 1:
                return "پربازدیدترین ها";
            case 2:
                return "پرفروش ترین ها";
            default:return "";


        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=new Fragment();
       switch (position){
           case 0:fragment= recycle_fragmenr.newInstance();
           break;
           case 1: fragment= fragment2.newInstance();
           break;
           case 2:fragment= firstfragment.newInstance();
               break;
           case 3:fragment=fragment2.newInstance();

       }
       return fragment;

    }

    @Override
    public int getCount() {
        return 3;
    }
}
