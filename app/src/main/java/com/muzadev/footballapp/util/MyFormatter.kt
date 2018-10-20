package com.muzadev.footballapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zulfakar on 20/10/18.
 * For educational purposes
 */
object MyFormatter {
    @SuppressLint("SimpleDateFormat")
    fun dateFormatter(input: String?): String {
        val date = SimpleDateFormat("dd/MM/yy").parse(input)
        return SimpleDateFormat("EEE, dd MMM yyy").format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun timeFormatter(input: String?): String {
        val inputFormat = SimpleDateFormat("HH:mm:ssXXX", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("Etc/ETC")
        val time = inputFormat.parse(input)

        return SimpleDateFormat("HH:mm").format(time)
    }
}