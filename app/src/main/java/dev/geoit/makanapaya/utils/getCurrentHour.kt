package dev.geoit.makanapaya.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun getCurrentHour(): Int {
    val datetimeValue = Date(System.currentTimeMillis())
    val simpleDateFormat = SimpleDateFormat("HH")
    return simpleDateFormat.format(datetimeValue).toInt()
}