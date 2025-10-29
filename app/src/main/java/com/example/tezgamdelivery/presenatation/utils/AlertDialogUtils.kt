package com.example.tezgamdelivery.presenatation.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.KeyEvent

object AlertDialogUtils {

    @SuppressLint("SuspiciousIndentation")
    fun showNoInternetDialog(context: Context, activity: Activity) {
     val dialog =  AlertDialog.Builder(context)
            .setTitle("No Internet Connection")
            .setMessage("Please check your mobile data or Wi-Fi.")
            .setPositiveButton("OK") { _, _ -> activity.finish() }
            .setCancelable(false)
            .create()

        dialog.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                activity.finishAffinity()
                true
            } else {
                false
            }
        }


        dialog.setCanceledOnTouchOutside(false)

        dialog.show()
    }
}