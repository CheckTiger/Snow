package cn.sxh.snowfox.UI.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import cn.sxh.snowfox.R;

public class NewsActivity extends AppCompatActivity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detail);
        initUI();
    }

    private void initUI() {
        mWebView = (WebView) findViewById(R.id.news_webView);
        loadData();
    }

    private void loadData() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(getIntent().getStringExtra("news_url"));
    }
}
