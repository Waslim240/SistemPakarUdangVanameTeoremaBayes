package com.waslim.sispakudangvaname.util

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(applicationContext, message , duration).show()

fun showLoading(isLoading: Boolean, progressBar: ProgressBar) {
    when {
        isLoading -> View.VISIBLE
        else -> View.GONE
    }.also { progressBar.visibility = it }
}