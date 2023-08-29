package com.imtiaj.m5education.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.imtiaj.m5education.R;
import com.imtiaj.m5education.modelClass.YoutubeUploadPOjo;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    private TextView Newfirst, NewsSecond;
    private ArrayList<YoutubeUploadPOjo> newsarrylist;
    private DatabaseReference NewsDatabase;

    private ImageView newsImage;
    private WebView newsWebView;
    private ProgressBar newsProgressBar;
    private LinearLayout ProgressLay;
    private SwipeRefreshLayout MainSwiperLay;
    private String CurrentURL, commingUrlByIntent;

    private LinearLayout MainLay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        // newsarrylist = new ArrayList<YoutubeUploadPOjo>();

        //NewsDatabase = FirebaseDatabase.getInstance().getReference().child("CreerL/YoutubeData");

        newsImage = (ImageView)findViewById(R.id.NewsImageView);
        newsWebView = (WebView)findViewById(R.id.NewsWebView);
        newsProgressBar = (ProgressBar)findViewById(R.id.NewsProgressbar);
        ProgressLay = (LinearLayout)findViewById(R.id.NewsProgressImageLay);
        //MainSwiperLay = (SwipeRefreshLayout)findViewById(R.id.NewsswiperLay);

        Bundle NewsCommingURL = getIntent().getExtras();
        commingUrlByIntent = NewsCommingURL.getString("NewsUrl");

        Log.d("NewActivity CheckURL",commingUrlByIntent);

        newsProgressBar.setProgress(100);

        newsWebView.loadUrl(commingUrlByIntent);
        newsWebView.getSettings().setJavaScriptEnabled(true);
        newsWebView.getSettings().setSupportZoom(true);
        newsWebView.getSettings().setBuiltInZoomControls(true);
        newsWebView.getSettings().setDisplayZoomControls(false);
        newsWebView.setWebViewClient(new WebViewClient(){



            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                ProgressLay.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                ProgressLay.setVisibility(View.GONE);

                //MainSwiperLay.setRefreshing(false);
                CurrentURL = url;
                super.onPageFinished(view, url);

            }
        });


        newsWebView.setWebChromeClient(new WebChromeClient(){

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                newsProgressBar.setProgress(newProgress);

                super.onProgressChanged(view, newProgress);

            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);

                newsImage.setImageBitmap(icon);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater newsmenu = getMenuInflater();
        newsmenu.inflate(R.menu.news_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.NewsmenuBackword:
                onBackPressed();

                break;

            case R.id.NewsmenuForword:
                onForwordPressed();

                break;

            case R.id.NewsMenuRefresh:
                newsWebView.reload();

                break;

            case R.id.NewsMenuShare:
                Intent ShareNews = new Intent(Intent.ACTION_SEND);
                ShareNews.setType("text/plain");
                ShareNews.putExtra(Intent.EXTRA_TEXT,CurrentURL);
                ShareNews.putExtra(Intent.EXTRA_SUBJECT,"Copied URL");
                startActivity(Intent.createChooser(ShareNews,"Share with friends"));

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onForwordPressed(){

        if (newsWebView.canGoForward()){
            newsWebView.goForward();
        }
        else {
            Toast.makeText(NewsActivity.this, "Can't Go Forword", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onBackPressed() {
        if (newsWebView.canGoBack()){
            newsWebView.goBack();
        }
        else {
            finish();
        }

    }


}
