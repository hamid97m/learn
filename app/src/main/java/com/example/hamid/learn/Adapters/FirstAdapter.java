package com.example.hamid.learn.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hamid.learn.R;
import com.example.hamid.learn.View.MainActivity;

/**
 * Created by Hamid on 8/16/2017.
 */

public class FirstAdapter  extends PagerAdapter {
    public  int change;



    Context context;

    int[] imageId = {R.mipmap.temple, R.mipmap.bag, R.mipmap.burger, R.mipmap.milad, R.mipmap.cake,R.mipmap.all};
    String[] title={"عجیب ترین مکان های تاریخی شهر خودتو پیدا کن و لذت ببر !",
            "پرتخفیف ترین و بهترین فروشگاه های مختلف رو کشف کن ! ",
            "خوشمزه ترین و به صرفه ترین غذاهای ممکن رو میل کنید",
            "بزن به دل طبیعت و مکان های گردشگری جدیدی رو تجربه کن !",
            "بهترین و دنج ترین کافه های شهرت رو بشناس",
            "همه در یک نگاه"};

    public FirstAdapter(Context context){
        this.context = context;

    }




    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.items_firstactivity, container, false);
        ImageView imageView = (ImageView) viewItem.findViewById(R.id.viewpager_image);
        TextView textView=(TextView)viewItem.findViewById(R.id.txt_caption);
        textView.setTypeface(MainActivity.b_typeface);
        textView.setText(title[position]);
        imageView.setImageResource(imageId[position]);


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
