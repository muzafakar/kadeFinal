package com.muzadev.footballapp.api

import com.muzadev.footballapp.model.Team
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
interface Api {

    @GET("search_all_teams.php")
    fun getTeams(@Query("l") league: String): Call<List<Team>>

    companion object {
        fun create(): Api {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                    .build()

            return retrofit.create(Api::class.java)
        }
    }
}