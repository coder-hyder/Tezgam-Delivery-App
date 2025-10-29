package com.example.tezgamdelivery.presenatation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AlertDialogBox(
                   onDismiss:()->Unit,
                   onConfirm:()-> Unit) {

       AlertDialog(
           onDismissRequest = { onDismiss() },
           confirmButton = {
               TextButton(onClick = { onConfirm()}) {
                   Text("Yes")
               }
           },
           dismissButton = {
               TextButton(onClick = { onDismiss() }) {
                   Text("No")
               }
           },

           title = { Text("Exit Application") },
           text = { Text("Are you sure you want to close this app?") },
           icon = { Icon(Icons.Default.Warning, contentDescription = "Icon",
               tint = Color.Red) }

       )
   }