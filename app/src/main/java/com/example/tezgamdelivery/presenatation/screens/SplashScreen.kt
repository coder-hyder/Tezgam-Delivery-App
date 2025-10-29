package com.example.tezgamdelivery.presenatation.screens

import android.app.Activity
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tezgamdelivery.R
import com.example.tezgamdelivery.presenatation.components.ProgressIndicator

@Composable
fun SplashScreen() {
    var scale by remember { mutableStateOf(0f) }

    val animatedScale by animateFloatAsState(
        targetValue = scale,
        animationSpec = tween(
            durationMillis = 1200,
            easing = FastOutSlowInEasing
        ),
        label = "scaleAnimation"
    )

    LaunchedEffect(Unit) {
        scale = 1f
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // 🖼 Logo
            Image(
                painter = painterResource(id = R.drawable.splash_logo),
                contentDescription = "Splash Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .graphicsLayer(
                        scaleX = animatedScale,
                        scaleY = animatedScale,
                        alpha = animatedScale
                    )
            )


            Spacer(modifier = Modifier.height(10.dp))

            // 🔸 Linear Progress Indicator
            ProgressIndicator()
        }
    }
}