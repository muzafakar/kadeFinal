package com.muzadev.footballapp.api

/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
object Api {
    private const val BASE_URL = "https://www.thesportsdb.com/api/v1/json/1/"

    fun getTeams(league: String?): String {
        return "${BASE_URL}search_all_teams.php?l=$league"
    }

    fun getNextMatches(leagueId: String?): String {
        return "${BASE_URL}eventsnextleague.php?id=$leagueId"
    }

    fun getLastMatch(leagueId: String?): String {
        return "${BASE_URL}eventspastleague.php?id=$leagueId"
    }
}