package com.example.hamid.learn.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamid.learn.Adapters.FirstAdapter;
import com.example.hamid.learn.Api.VersionApi;
import com.example.hamid.learn.BuildConfig;
import com.example.hamid.learn.Interfaces.OnVersionRecive;
import com.example.hamid.learn.R;
import com.github.vivchar.viewpagerindicator.ViewPagerIndicator;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    private ViewPager viewPager;
    String daste_title;
    private AutoCompleteTextView actv;
    private TextView txt_now_ostan;
    public static SharedPreferences.Editor editor;
    public static Boolean is_premium;

    private boolean checkostan;

    private  String ostan;
    private  ArrayAdapter<String> adapterr;


    String[] fruits = {"آذربایجان شرقی", "آذربایجان غربی", "اردبیل", "اصفهان", "البرز", "ایلام", "بوشهر", "تهران"
            , "چهارمحال و بختیاری", "خراسان جنوبی", "خراسان رضوی", "خراسان شمالی", "خوزستان", "زنجان", "سمنان", "سیستان و بلوچستان",
            "فارس", "قزوین", "قم"
            , "کردستان", "کرمان", "کرمانشاه", "کهگیلویه و بویراحمد", "گلستان", "گیلان", "لرستان", "مازندران", "مرکزی", "هرمزگان", "همدان"
            , "یزد"};
  private ArrayList<String> ostans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        SharedPreferences preferences_sava_status = getSharedPreferences("sava_status", 0);

       is_premium=preferences_sava_status.getBoolean("IS_PREMIUM", false);



        ostans =new ArrayList<>();
for(int i=1;i<fruits.length-2;i++){
            ostans.add(fruits[i]);
        }
        editor = getPreferences(MODE_PRIVATE).edit();
//        editor.putString("text", mSaved.getText().toString());

        txt_now_ostan=(TextView)findViewById(R.id.txt_now_ostan) ;


        //Creating the instance of ArrayAdapter containing list of fruit names
         adapterr = new ArrayAdapter<>
                 (this, android.R.layout.select_dialog_item, ostans);

        //Getting the instance of AutoCompleteTextView
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapterr);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.WHITE);

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        ostan = prefs.getString("text", null);

        actv.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                txt_now_ostan.setText(actv.getText().toString());
                ostan=actv.getText().toString();


            }
        });


        if (ostan!=null)
        {
            txt_now_ostan.setText(ostan);
        }else{
            txt_now_ostan.setText("هنوز انتخاب نشده است");
        }



        TextView txt_update=(TextView)findViewById(R.id.txt_update);
        txt_update.setTypeface(MainActivity.n_typeface);



        viewPager=(ViewPager)findViewById(R.id.viewPagerr);
        PagerAdapter adapter = new FirstAdapter(this);
        viewPager.setAdapter(adapter);




        ViewPagerIndicator viewPagerIndicator=(ViewPagerIndicator)findViewById(R.id.view_pager_indicator);
        viewPagerIndicator.setupWithViewPager(viewPager);

//        viewPagerIndicator.addOnPageChangeListener(mOnPageChangeListener);


        Button letsgo=(Button)findViewById(R.id.letsgo);
        letsgo.setTypeface(MainActivity.n_typeface);


        VersionApi versionApi=new VersionApi();
        versionApi.CHECK(new OnVersionRecive() {
            @Override
            public void OnRecive(int version) {
                int CurrentVersion= BuildConfig.VERSION_CODE;
                if(version>CurrentVersion){
                   RelativeLayout update=(RelativeLayout)findViewById(R.id.update);
                    update.setVisibility(View.VISIBLE);
                    Animation animation   =    AnimationUtils.loadAnimation(FirstActivity.this, R.anim.todown);
                    update.setAnimation(animation);

                }
            }
        });





        letsgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ostan!=null){
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                switch (viewPager.getCurrentItem()){
                    case 0:daste_title="tarikhi";break;
                    case 1:daste_title="kharid";break;
                    case 2:daste_title="resturant";break;
                    case 3:daste_title="gardesh";break;
                    case 4:daste_title="cafee";break;
                    case 5:daste_title="all";break;
                }

                intent.putExtra("daste", daste_title);
                if (txt_now_ostan.getText().toString().length()>1){
                    checkostan=false;
                    for(int i=0;i<fruits.length;i++){
                        if (txt_now_ostan.getText().toString().equals(fruits[i])){
                           checkostan=true;
                        }}
                        if (checkostan) {
                            checkostan=false;
                            intent.putExtra("ostan", txt_now_ostan.getText().toString());
                            editor.putString("text", txt_now_ostan.getText().toString());
                            editor.putString("text", txt_now_ostan.getText().toString());
                            editor.apply();
                            startActivity(intent);
                        }else{
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_no_ostan, (ViewGroup) findViewById(R.id.saggg));
                            TextView text = (TextView) layout.findViewById(R.id.text);
                            text.setTypeface(MainActivity.l_typeface);
                            text.setText("استان مشخص شده در لیست موجود نمیباشد  (در قسمت تغیر استان استان خود را مشخص کنید)");

                            Toast toast = new Toast(getApplicationContext());
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        }


                }
                else{
                    for(int i=0;i<fruits.length;i++){
                        if (ostan.equals(fruits[i])){
                            checkostan=true;
                        }}
                        if (checkostan) {
                            checkostan=false;
                            intent.putExtra("ostan",ostan );
                            startActivity(intent);
                        }else{
                            LayoutInflater inflater = getLayoutInflater();
                            View layout = inflater.inflate(R.layout.toast_no_ostan, (ViewGroup) findViewById(R.id.saggg));
                            TextView text = (TextView) layout.findViewById(R.id.text);
                            text.setTypeface(MainActivity.l_typeface);
                            text.setText("استان مشخص شده در لیست موجود نمیباشد  (در قسمت تغیر استان استان خود را مشخص کنید)");

                            Toast toast = new Toast(getApplicationContext());
                            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(layout);
                            toast.show();
                        }



                }
                }else{
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.saggg));
                    TextView text = (TextView) layout.findViewById(R.id.text);
                    text.setTypeface(MainActivity.l_typeface);
                    text.setText("لطفا استان خود را مشخص نمایید ");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);
                    toast.show();
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            doubleBackToExitPressedOnce=false;
                        }
                    }, 2000);

                }
            }
        });



    }


    // for exit in back and kill app
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            android.os.Process.killProcess(android.os.Process.myPid());
            return;
        }

        this.doubleBackToExitPressedOnce = true;

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.saggg));

        ImageView image = (ImageView) layout.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_settings_backup_restore_black_24dp);
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setTypeface(MainActivity.l_typeface);
        text.setText("برای خروج لطفا دکمه بازگشت را دوباره فشار دهید");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
//// TODO: 8/30/2017    یادم باشه یه چیزی بزارم مث تبلیغات هوشمند , هر کی خواست ثبت کنه بیاد پول بده فقط عم تا شعاع 50 کیلومتری مثلا نشون بده بعد اینجوری باشه که سه تا درجه شعاع داشته باشه از ده تاصد هی قیمتش بیشتر شه