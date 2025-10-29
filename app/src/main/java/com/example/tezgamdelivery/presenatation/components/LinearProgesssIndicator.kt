package com.example.tezgamdelivery.presenatation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(modifier: Modifier = Modifier) {
    LinearProgressIndicator(
        color = Color(0xfff5c842),
        trackColor = Color(0xFFE0E0E0),
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(4.dp)

    )

}