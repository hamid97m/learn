package com.example.hamid.learn.View;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hamid.learn.Adapters.CustomAdapter;
import com.example.hamid.learn.Interfaces.UploadImageInterface;
import com.example.hamid.learn.Model.UploadObject;
import com.example.hamid.learn.R;
import com.victor.loading.rotate.RotateLoading;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Upload extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    private static final String SERVER_PATH = "http://www.hardroid.ir/shop/";
    private MultipartBody.Part fileToUpload;
    private  RequestBody filename;
    private  EditText edt_value;
    private  EditText edt_dis;
    private  EditText edt_name;
    private  EditText edt_mark;
    private  EditText edt_sumery;
    private  EditText edt_phone;
    private  AutoCompleteTextView ostan;
    private SwitchCompat switchCompat;
    private  ImageView uploadimage;
    private  ScrollView mainScrollView;
    private RotateLoading rotateLoading;
    private Bitmap preview_bitmap;

    private int special;
    private int degreeofspecial;

    private boolean checkostan=false;

   private String[] fruits = {"آذربایجان شرقی", "آذربایجان غربی", "اردبیل", "اصفهان", "البرز", "ایلام", "بوشهر", "تهران"
            , "چهارمحال و بختیاری", "خراسان جنوبی", "خراسان رضوی", "خراسان شمالی", "خوزستان", "زنجان", "سمنان", "سیستان و بلوچستان",
            "فارس", "قزوین", "قم", "کردستان", "کرمان", "کرمانشاه", "کهگیلویه و بویراحمد", "گلستان", "گیلان", "لرستان", "مازندران", "مرکزی",
           "هرمزگان", "همدان", "یزد"};

    public static LinearLayout ll_map;
    private   LinearLayout ll_image;

    //for save location
    public static double latitude;
    public static double longitude=0;

    private  ViewPager viewPager;

    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        Bundle extras = getIntent().getExtras();
        special= extras.getInt("special");
        degreeofspecial= extras.getInt("degreeofspecial");


        ArrayAdapter<String> adapterr = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, fruits);
        //Getting the instance of AutoCompleteTextView
         ostan = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView3);
        ostan.setThreshold(1);//will start working from first character
        ostan.setAdapter(adapterr);//setting the adapter data into the AutoCompleteTextView
        ostan.setTextColor(Color.BLACK);


        viewPager=(ViewPager)findViewById(R.id.viewPagerr);
        PagerAdapter adapter = new CustomAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(2);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.7f);
                page.setScaleY(normalizedposition / 2 + 0.7f);
            }
        });
        viewPager.setClipToPadding(false);
        viewPager.setPadding(160,0,160,0);
viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String[] title={"نام مکان تاریخی","نام مکان خرید","نام رستوران"," نام مکان گردشگری"," نام کافه"};

        edt_name.setHint(title[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});

       Button btn_map=(Button)findViewById(R.id.btn_map);
        btn_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Upload.this,MapActivity.class));
            }
        });


        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Log.d("MyApp", "No SDCARD");
        } else {
            File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"learn");
            directory.mkdirs();
            Log.i("MyApp", "No SDCARD   make succesfully");
        }

        edt_value=(EditText)findViewById(R.id.edt_value);
        edt_value.setText("0");
        edt_dis=(EditText)findViewById(R.id.edt_discription);
        edt_name=(EditText)findViewById(R.id.edt_name);
        edt_mark=(EditText)findViewById(R.id.edt_mark);
        edt_sumery=(EditText)findViewById(R.id.edt_sumer);
        edt_phone=(EditText)findViewById(R.id.edt_phone);

        switchCompat=(SwitchCompat)findViewById(R.id.checktakhfif) ;

        edt_name.addTextChangedListener(new GenericTextWatcher(edt_name));
        edt_dis.addTextChangedListener(new GenericTextWatcher(edt_dis));
        edt_value.addTextChangedListener(new GenericTextWatcher(edt_value));
        edt_mark.addTextChangedListener(new GenericTextWatcher(edt_mark));
        edt_sumery.addTextChangedListener(new GenericTextWatcher(edt_sumery));
        edt_phone.addTextChangedListener(new GenericTextWatcher(edt_phone));


        ll_map=(LinearLayout)findViewById(R.id.ll_map);
        ll_image=(LinearLayout)findViewById(R.id.ll_image);

        uploadimage=(ImageView)findViewById(R.id.uploadimage);

        Button send_btn=(Button)findViewById(R.id.send_btn);

         mainScrollView=(ScrollView)findViewById(R.id.mainscroll);

        TextView picture_upload_text=(TextView)findViewById(R.id.picture_upload_text);
        TextView map=(TextView)findViewById(R.id.map);


        edt_name.setTypeface(MainActivity.l_typeface);
        edt_value.setTypeface(MainActivity.l_typeface);
        edt_mark.setTypeface(MainActivity.l_typeface);
        edt_sumery.setTypeface(MainActivity.l_typeface);
        edt_dis.setTypeface(MainActivity.l_typeface);
        picture_upload_text.setTypeface(MainActivity.n_typeface);
        map.setTypeface(MainActivity.n_typeface);
        send_btn.setTypeface(MainActivity.l_typeface);

//        value=Integer.parseInt(edt_value.getText().toString());

        Button selectUploadButton = (Button)findViewById(R.id.select_image);
        selectUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK);
                openGalleryIntent.setType("image/*");
                startActivityForResult(openGalleryIntent, REQUEST_GALLERY_CODE);
            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendtoapi(fileToUpload,filename);
                mainScrollView.fullScroll(ScrollView.FOCUS_UP);

            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if (!isChecked){
                   edt_value.setVisibility(View.GONE);
                   edt_value.setText("0");

               }else{
                   edt_value.setText("");
                   edt_value.setVisibility(View.VISIBLE);
               }
            }
        });

//________________________________________________________________________________________________
        // سبز شدن کادر استان بعد از پر شدنش
        ostan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ostan.setBackgroundResource(R.drawable.border_after);
                ostan.setTextColor(ContextCompat.getColor(Upload.this,R.color.black_with_opacity));
                ostan.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp,0,0,0);
            }
        });
        ostan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(ostan.getText().length()<1){
                    ostan.setBackgroundResource(R.drawable.border);
                    ostan.setTextColor(Color.BLACK);
                    ostan.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);

                }
            }
        });
//__________________________________________________________________________________________________

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, Upload.this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY_CODE && resultCode == Activity.RESULT_OK){
            uri = data.getData();
            if(EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                String filePath = getRealPathFromURIPath(uri, Upload.this);
                Log.i("saaaaaaaaalam",""+filePath);
                File file = new File(filePath);
                String path=Environment.getExternalStorageDirectory()+"/learn/"+file.getName();
                Log.i("madarsag",""+path);
                File filee = new File(path);
                if(file.exists()) {
                    ll_image.setBackgroundResource(R.drawable.border_after);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 8;
                    preview_bitmap = decodeFile(file);
                    uploadimage.setImageBitmap(preview_bitmap);

                    if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        OutputStream os;
                        try {
                            os = new FileOutputStream(filee);
                            preview_bitmap.compress(Bitmap.CompressFormat.PNG, 70, os);
                        os.flush();
                        os.close();
                        } catch (Exception e) {
                            Log.e(getClass().getSimpleName(), "saaaaaaaaaaaaag", e);
                        }
                    }
                    else{
                        EasyPermissions.requestPermissions(this, getString(R.string.write_file), READ_REQUEST_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    }
                }

//                Picasso.with(Upload.this).load(file).into(file);
                Log.d(TAG, "Filename " + file.getName());
                RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), filee);
              fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
                 filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

            }else{
                EasyPermissions.requestPermissions(this, getString(R.string.read_file), READ_REQUEST_CODE, Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        if(uri != null){
            String filePath = getRealPathFromURIPath(uri, Upload.this);
            File file = new File(filePath);
            String path=Environment.getExternalStorageDirectory()+"/learn/"+file.getName();
            File filee = new File(path);

            if(file.exists()) {
                ll_image.setBackgroundResource(R.drawable.border_after);
//                Bitmap myBitmap = decodeFile(file);
                preview_bitmap= decodeFile(file);
                uploadimage.setImageBitmap(preview_bitmap);
                if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    OutputStream os;
                    try {
                        os = new FileOutputStream(filee);
                        preview_bitmap.compress(Bitmap.CompressFormat.PNG, 70, os);
                        os.flush();
                        os.close();
                    } catch (Exception e) {
                        Log.e(getClass().getSimpleName(), "saaaaaaaaaaaaag", e);
                    }
                }
                else{
                    EasyPermissions.requestPermissions(this, getString(R.string.write_file), READ_REQUEST_CODE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                }
            }
            RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), filee);
            MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
            RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            sendtoapi(fileToUpload,filename);
        }
    }
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Log.d(TAG, "Permission has been denied");
    }

//________________________________________________________________________________________________
    public   void sendtoapi(MultipartBody.Part fileToUpload,RequestBody RequestBody){
        if(
                edt_name.getText().length()>=5&
                edt_sumery.getText().length()>=5&
                edt_phone.getText().length()>=8&
                edt_mark.getText().length()>=3&
                edt_dis.getText().length()>=10&
                longitude!=0&
                preview_bitmap!=null&
                ostan.getText().length()>=1)

        {
            for(int i=0;i<fruits.length;i++){
                if (ostan.getText().toString().equals(fruits[i])){
                    checkostan=true;
                }}
            if (checkostan) {
                checkostan = false;


                rotateLoading = (RotateLoading) findViewById(R.id.rotateLoadingupload);
                rotateLoading.start();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(SERVER_PATH)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                String daste_title = "";
                switch (viewPager.getCurrentItem()) {
                    case 0:
                        daste_title = "tarikhi";
                        break;
                    case 1:
                        daste_title = "kharid";
                        break;
                    case 2:
                        daste_title = "resturant";
                        break;
                    case 3:
                        daste_title = "gardesh";
                        break;
                    case 4:
                        daste_title = "cafee";
                        break;
                }


                int value = Integer.parseInt(edt_value.getText().toString());

                RequestBody discription = RequestBody.create(MediaType.parse("s"), edt_dis.getText().toString());
                RequestBody name = RequestBody.create(MediaType.parse("s"), edt_name.getText().toString());
                RequestBody sumerry = RequestBody.create(MediaType.parse("s"), edt_sumery.getText().toString());
                RequestBody phone = RequestBody.create(MediaType.parse("s"), edt_phone.getText().toString());
                RequestBody mark = RequestBody.create(MediaType.parse("s"), edt_mark.getText().toString());
                RequestBody daste = RequestBody.create(MediaType.parse("s"), daste_title);
                RequestBody ostan = RequestBody.create(MediaType.parse("s"), this.ostan.getText().toString());
                HashMap<String, RequestBody> map = new HashMap<>();
                map.put("discription", discription);
                map.put("mainname", name);
                map.put("mark", mark);
                map.put("sumerry", sumerry);
                map.put("phone", phone);
                map.put("daste", daste);
                map.put("ostan", ostan);

                HashMap<String, Integer> mapm = new HashMap<>();
                mapm.put("value", value);
                mapm.put("special", special);
                mapm.put("degreeofspecial", degreeofspecial);

                HashMap<String, Double> location = new HashMap<>();
                location.put("latitude", latitude);
                location.put("longitude", longitude);


                UploadImageInterface uploadImage = retrofit.create(UploadImageInterface.class);
                Call<UploadObject> fileUpload = uploadImage.uploadFile(fileToUpload, filename, map, mapm, location);
                fileUpload.enqueue(new Callback<UploadObject>() {
                    @Override
                    public void onResponse(Call<UploadObject> call, Response<UploadObject> response) {
                        rotateLoading.stop();
                        Toast.makeText(Upload.this, "با موفقیت انجام شد", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<UploadObject> call, Throwable t) {
                        Log.d("sagbawsag", t.getMessage());
                        rotateLoading.stop();
                    }
                });
            }else{
                Toast.makeText(Upload.this,"استان انتخاب شده در لیست موجود نیست",Toast.LENGTH_LONG).show();

            }
    }
        else if (longitude == 0) Toast.makeText(Upload.this, "لطفا آدرس مورد نظر را روی نقشه نشان دهید", Toast.LENGTH_SHORT).show();
        else  if(preview_bitmap==null)Toast.makeText(Upload.this, "لطفا عکس مورد نظر خودتونو انتخاب کنید", Toast.LENGTH_SHORT).show();
        else Toast.makeText(Upload.this, "لطفا فیلدها رو به درستی و کامل پرکنید", Toast.LENGTH_SHORT).show();
    }
    //______________________________________________________________________________________________

    private Bitmap decodeFile(File f) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // The new size we want to scale to
            final int REQUIRED_SIZE=200;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while(o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

//text watcher for edt texts
private class GenericTextWatcher implements TextWatcher{

    private View view;
    private GenericTextWatcher(View view) {
        this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    public void afterTextChanged(Editable editable) {
        switch(view.getId()){
            case R.id.edt_discription:
                if(edt_dis.getText().length()>=10){
                    edt_dis.setBackgroundResource(R.drawable.border_after);
                    edt_dis.setTextColor(ContextCompat.getColor(Upload.this,R.color.black_with_opacity));
                    edt_dis.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp,0,0,0);
                }
                if(edt_dis.getText().length()<10){
                    edt_dis.setBackgroundResource(R.drawable.border);
                    edt_dis.setTextColor(Color.BLACK);
                    edt_dis.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);

                }
                break;
            case R.id.edt_mark:
                if(edt_mark.getText().length()>=3){
                    edt_mark.setBackgroundResource(R.drawable.border_after);
                    edt_mark.setTextColor(ContextCompat.getColor(Upload.this,R.color.black_with_opacity));
                    edt_mark.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp,0,0,0);

                }
                if(edt_mark.getText().length()<3){
                    edt_mark.setBackgroundResource(R.drawable.border);
                    edt_mark.setTextColor(Color.BLACK);
                    edt_mark.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);

                }
                break;
            case R.id.edt_name:
                if(edt_name.getText().length()>=5){
                    edt_name.setBackgroundResource(R.drawable.border_after);
                    edt_name.setTextColor(ContextCompat.getColor(Upload.this,R.color.black_with_opacity));
                    edt_name.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp,0,0,0);

                }
                if(edt_name.getText().length()<5){
                    edt_name.setBackgroundResource(R.drawable.border);
                    edt_name.setTextColor(Color.BLACK);
                    edt_name.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);

                }
                break;
            case R.id.edt_sumer:
                if(edt_sumery.getText().length()>=5){
                    edt_sumery.setBackgroundResource(R.drawable.border_after);
                    edt_sumery.setTextColor(ContextCompat.getColor(Upload.this,R.color.black_with_opacity));
                    edt_sumery.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp,0,0,0);


                }
            case R.id.edt_phone:
                if(edt_phone.getText().length()>=8){
                    edt_phone.setBackgroundResource(R.drawable.border_after);
                    edt_phone.setTextColor(ContextCompat.getColor(Upload.this,R.color.black_with_opacity));
                    edt_phone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp,0,0,0);


                }
                if(edt_sumery.getText().length()<5){
                    edt_sumery.setBackgroundResource(R.drawable.border);
                    edt_sumery.setTextColor(Color.BLACK);
                    edt_sumery.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);

                }
                break;
            case R.id.edt_value:
                if(edt_value.getText().length()>=1){
                    edt_value.setBackgroundResource(R.drawable.border_after);
                    edt_value.setTextColor(ContextCompat.getColor(Upload.this,R.color.black_with_opacity));
                    edt_value.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_done_black_24dp,0,0,0);

                }
                if(edt_value.getText().length()<1){
                    edt_value.setBackgroundResource(R.drawable.border);
                    edt_value.setTextColor(Color.BLACK);
                    edt_value.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);

                }
                break;

        }
    }
}

}
//TODO Get Phone Number From Useres And Orginal users