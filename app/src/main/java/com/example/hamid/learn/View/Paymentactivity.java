package com.example.hamid.learn.View;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hamid.learn.R;
import com.victor.loading.rotate.RotateLoading;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Paymentactivity extends Activity {
    public   WebView webview;
    public RotateLoading rotateLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentactivity);
        rotateLoading=(RotateLoading) findViewById(R.id.rotateloading);
        rotateLoading.start();


        Bundle extras = getIntent().getExtras();
        int value= extras.getInt("value");
        String mobile= extras.getString("mobile");
        String email= extras.getString("email");
        String discription= extras.getString("discription");
         webview = (WebView) findViewById(R.id.webView1);
//        setContentView(webview);
        String url = "http://www.hardroid.ir/shop/request.php";
        String postData = null;
        try {
            postData = "amount=" + value +
                    "&description=" + URLEncoder.encode(discription, "UTF-8") +
//                    "&mobile=" + URLEncoder.encode(mobile, "UTF-8")+
                    "&email=" + URLEncoder.encode(email, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);
        webview.setWebViewClient(new WebViewClient());
        webview.setWebChromeClient(new WebChromeClient());
        webview.postUrl(url,postData.getBytes());
        webview.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                rotateLoading.stop();
            }
        });





    }
}
