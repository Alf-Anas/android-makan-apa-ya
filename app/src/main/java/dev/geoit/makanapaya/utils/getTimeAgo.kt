package dev.geoit.makanapaya.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("SimpleDateFormat")
fun getTimeAgo(dateString: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val date = dateFormat.parse(dateString)

    val calendar = Calendar.getInstance()
    val currentTime = calendar.time
    val timeDifference = currentTime.time - date!!.time

    val secondsInMillis = 1000
    val minutesInMillis = secondsInMillis * 60
    val hoursInMillis = minutesInMillis * 60
    val daysInMillis = hoursInMillis * 24

    val formattedTime = SimpleDateFormat("HH:mm:ss").format(date)
    return when (kotlin.math.abs(timeDifference / daysInMillis)) {
        0L -> "Today at $formattedTime"
        1L -> "Yesterday at $formattedTime"
        2L -> "2 Days Ago at $formattedTime"
        3L -> "3 Days Ago at $formattedTime"
        4L -> "4 Days Ago at $formattedTime"
        5L -> "5 Days Ago at $formattedTime"
        else -> SimpleDateFormat("dd-MMM-yyyy HH:mm:ss").format(date)
    }
}