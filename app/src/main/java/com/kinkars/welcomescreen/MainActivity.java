package com.kinkars.welcomescreen;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("http://kinkars.com");
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);


        myWebView.setWebViewClient(new WebViewClient() {

                                       public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                           if (url != null && url.startsWith("tel:")) {
                                               view.getContext().startActivity(
                                                       new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                                               return true;
                                           } else if (url != null && url.startsWith("mailto:")) {
                                               view.getContext().startActivity(
                                                       new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                                               return true;
                                           }  else {
                                               return false;
                                           }
                                       }
                                           public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                                               try {
                                                   webView.stopLoading();
                                               } catch (Exception e) {
                                               }

                                               if (webView.canGoBack()) {
                                                   webView.goBack();
                                               }
                                               webView.loadUrl("about:blank");
                                               AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                                               alertDialog.setTitle("Error");
                                               alertDialog.setMessage("Check your internet connection and try again.");
                                               alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                                                   public void onClick(DialogInterface dialog, int which) {
                                                       finish();
                                                       startActivity(getIntent());
                                                   }
                                               });

                                               alertDialog.show();
                                               super.onReceivedError(webView, errorCode, description, failingUrl);
                                           }
                                       }
            );

        }


}
