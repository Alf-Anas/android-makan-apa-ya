package dev.geoit.makanapaya.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getCurrentTime(): String {
    val datetimeValue = Date(System.currentTimeMillis())
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return simpleDateFormat.format(datetimeValue)
}