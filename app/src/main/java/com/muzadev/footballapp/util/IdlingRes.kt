package com.muzadev.footballapp.util

import android.support.test.espresso.idling.CountingIdlingResource

/**
 * Created by zulfakar on 30/10/18.
 * For educational purposes
 */
object IdlingRes {
    val main = CountingIdlingResource("ini_main_idlingResource", true)
}