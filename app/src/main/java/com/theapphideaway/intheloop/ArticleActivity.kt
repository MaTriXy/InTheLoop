package com.theapphideaway.intheloop

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.net.http.SslError
import android.view.View
import android.webkit.*


class ArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        val myWebView: WebView = findViewById(R.id.web_view)
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
