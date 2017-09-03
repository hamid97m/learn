package com.example.hamid.learn.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hamid.learn.View.LikeFragment;
import com.example.hamid.learn.View.SeenFragment;
import com.example.hamid.learn.View.NewsFragment;
import com.example.hamid.learn.View.SpecialFragment;

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
                return "محبوب ترین ها";
            case 3:
                return "ویژه ترین";
            default:return "";


        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=new Fragment();
       switch (position){
           case 0:fragment= NewsFragment.newInstance();
           break;
           case 1: fragment= SeenFragment.newInstance();
           break;
           case 2:fragment= LikeFragment.newInstance();
               break;
           case 3:fragment= SpecialFragment.newInstance();
               break;
//           case 4:fragment= SpecialFragment.newInstance();
       }
       return fragment;

    }

    @Override
    public int getCount() {
        return 4;
    }
}
