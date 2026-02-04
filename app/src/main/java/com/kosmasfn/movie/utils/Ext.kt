package com.kosmasfn.movie.utils

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.kosmasfn.movie.BuildConfig
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun Context.showMessage(message: String?) {
    if (message != null && message != "") {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

fun String.replaceURL(): String {
    Log.d(TAG, this)
    val indexZero = if(this.contains(": Unknown error")) this.indexOf(": Unknown error") + 1 else 0
    val index = if(this.contains("URL")) this.indexOf("URL")-1 else this.length-1
    val message = this.substring(indexZero, index)
    return message.replace(BuildConfig.API_BASE_URL, "")
}

fun String.formatDate(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US)
        val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.US)
        try {
            val date = LocalDate.parse(this, parser)
            date.format(formatter)
        } catch (e: Exception) {
            this
        }
    } else {
        try {
            val parser = java.text.SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val formatter = java.text.SimpleDateFormat("dd MMM yyyy", Locale.US)
            val date = parser.parse(this)
            date?.let { formatter.format(it) } ?: this
        } catch (e: Exception) {
            this
        }
    }
}


