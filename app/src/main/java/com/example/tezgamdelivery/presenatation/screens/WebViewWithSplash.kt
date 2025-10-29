package com.example.tezgamdelivery.presenatation.screens

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.tezgamdelivery.presenatation.utils.NetworkUtils
import com.example.tezgamdelivery.presenatation.utils.NetworkUtils.isInternetAvailable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WebViewWithSplash(url: String) {
    val context = LocalContext.current
    val activity = context as Activity
    var showSplash by remember { mutableStateOf(true) }
    var isPageLoaded by remember { mutableStateOf(false) }
    var loadError by remember { mutableStateOf(false) }
    var isLoading by remember{mutableStateOf(false)}
    var showSlowInternetToast by remember { mutableStateOf(false) }
    var reloadKey by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()


    val webView = remember(reloadKey) {
        WebView(context).apply {
            settings.domStorageEnabled = true
            overScrollMode = WebView.OVER_SCROLL_NEVER

            webViewClient = object : WebViewClient() {


                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                    println("onReceivedError")
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)

                    isPageLoaded = false
                    println("onPageStarted")


                }
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    if (!loadError) {
                        isPageLoaded = true
                    }
                    println("onPageFinshed")
//
//                    println(isLoading)
//                    isPageLoaded = true
//                    loadError = false

                }



                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {

                    println("shouldOverrideUrlLoading")
                    if (!isInternetAvailable(context)) {
                        loadError = true
                        view?.stopLoading()
                        view?.loadUrl("about:blank")
                        return true
                    }
                    return false
                }


            }
        }

    }

    webView.webChromeClient = object : WebChromeClient() {


        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)


            println("onProgressChanged")
            // Update a ProgressBar

            // Show the ProgressBar while loading
            if (newProgress < 100 && !NetworkUtils.isInternetAvailable(context)) {
                loadError = true
                isPageLoaded = true
            }
        }


    }



    coroutineScope.launch {
        if (isPageLoaded){
            delay(5000)
            isLoading = false
        }
    }

    LaunchedEffect(isPageLoaded) {
//        if (isPageLoaded) {
//            showSplash = false
//        }

            if (!isPageLoaded) {


                delay(20_000)
                if (!isPageLoaded) {
                    Toast.makeText(
                        context,
                        "Internet slow, please wait — it may take some time",
                        Toast.LENGTH_LONG
                    ).show()
                }


                delay(10_000)
                if (!isPageLoaded) {
                    loadError = true
                    showSplash = false
                }
            } else {

                showSplash = false
            }

    }

    LaunchedEffect(Unit) {
        if (isInternetAvailable(context)) {
            webView.loadUrl(url)


        } else {
            loadError = true
            showSplash = false
        }
    }






    Box(modifier = Modifier.fillMaxSize()) {

        when {

            loadError -> {
                NoInternetScreen(
                    webView = webView,
                    url = url,
                    onReloadSuccess = {
                        loadError = false
                        showSplash = false
                        isPageLoaded = true
                        isLoading = true



//                        if (isInternetAvailable(context)) {
//                            loadError = false
//                            isLoading = true
//                            webView.loadUrl(url)
//                        } else {
//                            Toast.makeText(context, "Still no internet!", Toast.LENGTH_SHORT).show()
//                        }
                    },
                    context = context
                )
            }

            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(color = Color(0xfff5c842))
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(text = "Please wait...")
                    }
                }
            }

            !showSplash -> {
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn(tween(500)),
                    exit = fadeOut(tween(500))
                ) {
                    WebViewContent(
                        webView = webView,
                        onNoInternet = {
                            loadError = true
                            showSplash = false
                        }
                    )
                }
            }


            else -> {
                SplashScreen()
            }
        }
    }
}

