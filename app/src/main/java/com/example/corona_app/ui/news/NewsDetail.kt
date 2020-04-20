package com.example.corona_app.ui.news

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.corona_app.R
import kotlinx.android.synthetic.main.news_detail.*

class NewsDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_detail)
        val news = intent.getParcelableExtra(EXTRA_DATA) as NewsData
        supportActionBar?.title = news.source
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(news.url)
    }
    companion object {
        const val EXTRA_DATA = ""
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
