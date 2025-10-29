package com.example.tezgamdelivery.presenatation.screens


import android.webkit.WebView
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tezgamdelivery.presenatation.utils.NetworkUtils.isInternetAvailable

@Composable
fun NoInternetScreen(
    webView: WebView,
    url: String,
    onReloadSuccess: () -> Unit,
    context: android.content.Context
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "No Internet",
                tint = Color.Red,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No Internet Connection",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Please check your mobile data or Wi-Fi and try again.",
                color = Color.Gray,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (isInternetAvailable(context)) {
                        onReloadSuccess()
                        webView.reload()
                    } else {
                        Toast.makeText(context, "Still no internet connection", Toast.LENGTH_SHORT).show()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xfff5c842)),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Try Again", color = Color.Black, fontWeight = FontWeight.Bold)
            }
        }
    }
}
