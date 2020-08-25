package com.hawksjamesf.spacecraft

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * Copyright ® $ 2020
 * All right reserved.
 *
 * @author: hawksjamesf
 * @email: hawksjamesf@gmail.com
 * @since: Aug/24/2020  Mon
 */
class WebViewActivity : AppCompatActivity() {
    companion object{
        @JvmStatic
        fun startActivity(context:Context){
            //        Fragment fragment = WebViewFragment.newInstance("https://i.meituan.com/c/ZDg0Y2FhNjMt");
//        FragmentUtils.add(getSupportFragmentManager(),fragment,android.R.id.content);
//        FragmentUtils.show(fragment);
            val uri = Uri.parse("ispacecraft://www.spacecraft.com/web/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            intent.putExtra("url", "https://i.meituan.com/c/ZDg0Y2FhNjMt")
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val url = intent.getStringExtra("url")
        wv.loadUrl(url)
        wv.webViewClient = object : WebViewClient() {

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed()
                //handleMessage(Message msg); 其他处理
            }
                // 这行代码一定加上否则效果不会出现
        }
        wv.settings.javaScriptEnabled = true
    }
}