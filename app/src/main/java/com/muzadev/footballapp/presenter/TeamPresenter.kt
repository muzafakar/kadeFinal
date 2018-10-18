package com.muzadev.footballapp.presenter

import com.muzadev.footballapp.api.Api

/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
class TeamPresenter(private val view: TeamView) {
    private val api = Api.create()

    fun getTeams(league:String){
        val teams = api.getTeams("English Premiere League")
        val result = teams.execute().body()
    }
}
