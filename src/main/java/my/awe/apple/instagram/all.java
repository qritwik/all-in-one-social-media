package my.awe.apple.instagram;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class all extends AppCompatActivity {

    ImageView insta,fb,linkedin,youtube,gmail,twitter,reddit,quora,google,sayatme;
    private InterstitialAd mInterstitialAd;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.awe.apple.instagram.R.layout.activity_all);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        insta = (ImageView)findViewById(my.awe.apple.instagram.R.id.insta);
        fb = (ImageView)findViewById(my.awe.apple.instagram.R.id.fb);
        linkedin = (ImageView)findViewById(my.awe.apple.instagram.R.id.linkedin);
        youtube = (ImageView)findViewById(my.awe.apple.instagram.R.id.youtube);
        gmail = (ImageView)findViewById(my.awe.apple.instagram.R.id.gmail);
        twitter = (ImageView)findViewById(my.awe.apple.instagram.R.id.twitter);
        reddit = (ImageView)findViewById(my.awe.apple.instagram.R.id.reddit);
        quora = (ImageView)findViewById(my.awe.apple.instagram.R.id.quora);
        google = (ImageView)findViewById(my.awe.apple.instagram.R.id.google);
        sayatme = (ImageView)findViewById(my.awe.apple.instagram.R.id.sayatme);



        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8750480772839804/3779003383");

        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });


        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    Intent intent = new Intent(all.this,MainActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                    Intent intent = new Intent(all.this,MainActivity.class);
                    startActivity(intent);
                }


            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                Intent intent = new Intent(all.this,fb.class);
                startActivity(intent);

            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                Intent intent = new Intent(all.this,linkedin.class);
                startActivity(intent);

            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                Intent intent = new Intent(all.this,youtube.class);
                startActivity(intent);

            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent intent = new Intent(all.this,gmail.class);
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent intent = new Intent(all.this,twitter.class);
                startActivity(intent);
            }
        });

        quora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
                Intent intent = new Intent(all.this,quora.class);
                startActivity(intent);
            }
        });

        reddit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                Intent intent = new Intent(all.this,reddit.class);
                startActivity(intent);

            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                Intent intent = new Intent(all.this,google.class);
                startActivity(intent);

            }
        });

        sayatme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

                Intent intent = new Intent(all.this,sayatme.class);
                startActivity(intent);

            }
        });









    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=my.awe.apple.instagram");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        }
        if (id == R.id.action_abt) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=my.awe.apple.instagram"));
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
