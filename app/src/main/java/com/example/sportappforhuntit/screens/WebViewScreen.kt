package com.example.sportappforhuntit.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun MyWebView() {

    val mUrl = "https://liquipedia.net/counterstrike/Main_Page"

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(mUrl)
        }
    }, update = {
        it.loadUrl(mUrl)
    })
}