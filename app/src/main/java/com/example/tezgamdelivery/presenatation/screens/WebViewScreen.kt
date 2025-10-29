package com.example.tezgamdelivery.presenatation.screens

import android.app.Activity
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.tezgamdelivery.presenatation.components.AlertDialogBox
import com.example.tezgamdelivery.presenatation.utils.NetworkUtils.isInternetAvailable

@Composable
fun WebViewContent(
    webView: WebView,
    onNoInternet: () -> Unit,
) {
    val context = LocalContext.current
    val activity = context as Activity
    var showExitDialog by remember { mutableStateOf<Boolean>(false) }
    var isRefreshing by remember { mutableStateOf(false) }




    AndroidView(
        factory = {
            SwipeRefreshLayout(it).apply {
                setOnRefreshListener {
                    isRefreshing = true

                    // ✅ Check Internet before reloading
                    if (isInternetAvailable(context)) {
                        webView.reload()
                    } else {
                        // show custom no internet screen
                        onNoInternet()
                    }
                }
                (webView.parent as? SwipeRefreshLayout)?.removeView(webView)
                addView(webView)

            }
        },
        update = {
            it.isRefreshing = isRefreshing
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    it.isRefreshing = false
                    isRefreshing = false
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    // Handle back button
    BackHandler {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            showExitDialog = true
        }
    }

    if (showExitDialog) {
        AlertDialogBox(
            onDismiss = { showExitDialog = false },
            onConfirm = { activity.finish() })
    }


}
