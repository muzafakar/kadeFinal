package com.muzadev.footballapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Match(

        @SerializedName("intHomeShots")
        val intHomeShots: Any? = null,

        @SerializedName("strSport")
        val strSport: String? = null,

        @SerializedName("strHomeLineupDefense")
        val strHomeLineupDefense: Any? = null,

        @SerializedName("strAwayLineupSubstitutes")
        val strAwayLineupSubstitutes: Any? = null,

        @SerializedName("idLeague")
        val idLeague: String? = null,

        @SerializedName("idSoccerXML")
        val idSoccerXML: String? = null,

        @SerializedName("strHomeLineupForward")
        val strHomeLineupForward: Any? = null,

        @SerializedName("strTVStation")
        val strTVStation: Any? = null,

        @SerializedName("strHomeGoalDetails")
        val strHomeGoalDetails: Any? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        val strAwayLineupGoalkeeper: Any? = null,

        @SerializedName("strAwayLineupMidfield")
        val strAwayLineupMidfield: Any? = null,

        @SerializedName("idEvent")
        val idEvent: String? = null,

        @SerializedName("intRound")
        val intRound: String? = null,

        @SerializedName("strHomeYellowCards")
        val strHomeYellowCards: Any? = null,

        @SerializedName("idHomeTeam")
        val idHomeTeam: String? = null,

        @SerializedName("intHomeScore")
        val intHomeScore: Any? = null,

        @SerializedName("dateEvent")
        val dateEvent: String? = null,

        @SerializedName("strCountry")
        val strCountry: Any? = null,

        @SerializedName("strAwayTeam")
        val strAwayTeam: String? = null,

        @SerializedName("strHomeLineupMidfield")
        val strHomeLineupMidfield: Any? = null,

        @SerializedName("strDate")
        val strDate: String? = null,

        @SerializedName("strHomeFormation")
        val strHomeFormation: Any? = null,

        @SerializedName("strMap")
        val strMap: Any? = null,

        @SerializedName("idAwayTeam")
        val idAwayTeam: String? = null,

        @SerializedName("strAwayRedCards")
        val strAwayRedCards: Any? = null,

        @SerializedName("strBanner")
        val strBanner: Any? = null,

        @SerializedName("strFanart")
        val strFanart: Any? = null,

        @SerializedName("strDescriptionEN")
        val strDescriptionEN: Any? = null,

        @SerializedName("strResult")
        val strResult: Any? = null,

        @SerializedName("strCircuit")
        val strCircuit: Any? = null,

        @SerializedName("intAwayShots")
        val intAwayShots: Any? = null,

        @SerializedName("strFilename")
        val strFilename: String? = null,

        @SerializedName("strTime")
        val strTime: String? = null,

        @SerializedName("strAwayGoalDetails")
        val strAwayGoalDetails: Any? = null,

        @SerializedName("strAwayLineupForward")
        val strAwayLineupForward: Any? = null,

        @SerializedName("strLocked")
        val strLocked: String? = null,

        @SerializedName("strSeason")
        val strSeason: String? = null,

        @SerializedName("intSpectators")
        val intSpectators: Any? = null,

        @SerializedName("strHomeRedCards")
        val strHomeRedCards: Any? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        val strHomeLineupGoalkeeper: Any? = null,

        @SerializedName("strHomeLineupSubstitutes")
        val strHomeLineupSubstitutes: Any? = null,

        @SerializedName("strAwayFormation")
        val strAwayFormation: Any? = null,

        @SerializedName("strEvent")
        val strEvent: String? = null,

        @SerializedName("strAwayYellowCards")
        val strAwayYellowCards: Any? = null,

        @SerializedName("strAwayLineupDefense")
        val strAwayLineupDefense: Any? = null,

        @SerializedName("strHomeTeam")
        val strHomeTeam: String? = null,

        @SerializedName("strThumb")
        val strThumb: Any? = null,

        @SerializedName("strLeague")
        val strLeague: String? = null,

        @SerializedName("intAwayScore")
        val intAwayScore: Any? = null,

        @SerializedName("strCity")
        val strCity: Any? = null,

        @SerializedName("strPoster")
        val strPoster: Any? = null
) : Serializable

data class MatchResponse(

        @field:SerializedName("events")
        val events: List<Match?>? = null
)