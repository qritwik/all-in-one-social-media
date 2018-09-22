package my.awe.apple.instagram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class youtube extends AppCompatActivity {

    private AdView mAdView4;
    WebView webView4;
    ProgressBar spinner4;

    public Uri imageUri;

    private static final String TAG = youtube.class.getSimpleName();
    private String mCM;
    private ValueCallback<Uri> mUM;
    private ValueCallback<Uri[]> mUMA;
    private final static int FCR=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.awe.apple.instagram.R.layout.activity_youtube);

        MobileAds.initialize(this, "ca-app-pub-8750480772839804~5834999707");

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-8750480772839804/6891294025");

        mAdView4 = findViewById(my.awe.apple.instagram.R.id.adView_youtube);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView4.loadAd(adRequest);

        webView4 = (WebView) findViewById(my.awe.apple.instagram.R.id.webview_youtube);
        spinner4 = (ProgressBar)findViewById(my.awe.apple.instagram.R.id.progressBar_youtube);

        WebView myWebView = (WebView) findViewById(my.awe.apple.instagram.R.id.webview_youtube);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView4.getSettings().setDomStorageEnabled(true);

        webView4.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                spinner4.setVisibility(View.GONE);
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
                AlertDialog alertDialog = new AlertDialog.Builder(youtube.this).create();
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
        });


        webView4.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView4.setScrollbarFadingEnabled(false);


        webView4.loadUrl("https://m.youtube.com/");


        webView4.setWebChromeClient(new WebChromeClient(){
            //For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg){
                mUM = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                youtube.this.startActivityForResult(Intent.createChooser(i,"File Chooser"), FCR);
            }
            // For Android 3.0+, above method not supported in some android 3+ versions, in such case we use this
            public void openFileChooser(ValueCallback uploadMsg, String acceptType){
                mUM = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                youtube.this.startActivityForResult(
                        Intent.createChooser(i, "File Browser"),
                        FCR);
            }
            //For Android 4.1+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture){
                mUM = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                youtube.this.startActivityForResult(Intent.createChooser(i, "File Chooser"), youtube.FCR);
            }
            //For Android 5.0+
            public boolean onShowFileChooser(
                    WebView webView, ValueCallback<Uri[]> filePathCallback,
                    WebChromeClient.FileChooserParams fileChooserParams){
                if(mUMA != null){
                    mUMA.onReceiveValue(null);
                }
                mUMA = filePathCallback;
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(takePictureIntent.resolveActivity(youtube.this.getPackageManager()) != null){
                    File photoFile = null;
                    try{
                        photoFile = createImageFile();
                        takePictureIntent.putExtra("PhotoPath", mCM);
                    }catch(IOException ex){
                        Log.e(TAG, "Image file creation failed", ex);
                    }
                    if(photoFile != null){
                        mCM = "file:" + photoFile.getAbsolutePath();
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    }else{
                        takePictureIntent = null;
                    }
                }
                Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
                contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
                contentSelectionIntent.setType("*/*");
                Intent[] intentArray;
                if(takePictureIntent != null){
                    intentArray = new Intent[]{takePictureIntent};
                }else{
                    intentArray = new Intent[0];
                }

                Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
                chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
                chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentArray);
                startActivityForResult(chooserIntent, FCR);
                return true;
            }
        });









    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if(Build.VERSION.SDK_INT >= 21){
            Uri[] results = null;
            //Check if response is positive
            if(resultCode== Activity.RESULT_OK){
                if(requestCode == FCR){
                    if(null == mUMA){
                        return;
                    }
                    if(intent == null || intent.getData() == null){
                        //Capture Photo if no image available
                        if(mCM != null){
                            results = new Uri[]{Uri.parse(mCM)};
                        }
                    }else{
                        String dataString = intent.getDataString();
                        if(dataString != null){
                            results = new Uri[]{Uri.parse(dataString)};
                        }
                    }
                }
            }
            mUMA.onReceiveValue(results);
            mUMA = null;
        }else{
            if(requestCode == FCR){
                if(null == mUM) return;
                Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
                mUM.onReceiveValue(result);
                mUM = null;
            }
        }
    }


    public class Callback extends WebViewClient{
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl){
            Toast.makeText(getApplicationContext(), "Failed loading app!", Toast.LENGTH_SHORT).show();
        }
    }
    // Create an image file
    private File createImageFile() throws IOException{
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "img_"+timeStamp+"_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName,".jpg",storageDir);
    }
    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event){
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode){
                case KeyEvent.KEYCODE_BACK:
                    if(webView4.canGoBack()){
                        webView4.goBack();
                    }else{
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onBackPressed() {

        if(webView4.canGoBack()){
            webView4.goBack();
            return;
        }
        super.onBackPressed();

    }




}
