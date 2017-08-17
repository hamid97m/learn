package com.example.hamid.learn.Adapters;

/**
 * Created by Hamid on 4/4/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.learn.R;
import com.example.hamid.learn.View.MainActivity;

public class CustomAdapter extends PagerAdapter{
    public  int change;



    Context context;

    int[] imageId = {R.mipmap.temple, R.mipmap.bag, R.mipmap.burger, R.mipmap.milad, R.mipmap.cake};
    String[] title={"تاریخی","خرید","رستوران","گردشگری","کافه"};

    public CustomAdapter(Context context){
        this.context = context;

    }




    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.image_item, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.viewpager_image);
        TextView textView=(TextView)viewItem.findViewById(R.id.viewpager_title);
        textView.setTypeface(MainActivity.l_typeface);
        textView.setText(title[position]);
        imageView.setImageResource(imageId[position]);

        Log.i("viewpager",""+position);
        container.addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return imageId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // TODO Auto-generated method stub

        return view == (object);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }




}