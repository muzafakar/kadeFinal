package com.muzadev.footballapp.api

import java.net.URL

/**
 * Created by zulfakar on 19/10/18.
 * For educational purposes
 */
class ApiRepo {
    fun doRequest(url: String): String {
        return URL(url).readText()
    }
}