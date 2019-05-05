package com.theapphideaway.intheloop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.net.http.SslError
import android.view.View
import android.view.Window
import android.webkit.*
import android.webkit.WebViewClient
import android.webkit.WebView
import android.webkit.WebChromeClient
import android.widget.ProgressBar


class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().requestFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_article)
        val myWebView: WebView = findViewById(R.id.web_view)
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);


        myWebView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                val progressbar = findViewById<ProgressBar>(R.id.progress_bar)
                progressbar.visibility = View.GONE
            }
        }

        var mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = getString(R.string.app_name)
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)

        try{
            var bundle:Bundle=intent.extras
            myWebView.loadUrl(bundle.getString("Url") )
        }catch (ex:Exception){

        }

        mToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
