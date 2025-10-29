package com.example.tezgamdelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.tezgamdelivery.presenatation.screens.WebViewWithSplash
import com.example.tezgamdelivery.ui.theme.TezgamDeliveryTheme

class MainActivity : ComponentActivity() {
    private val websiteURL = "https://tezgamdelivery.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TezgamDeliveryTheme {
                WebViewWithSplash(websiteURL)
            }
        }
    }
}

