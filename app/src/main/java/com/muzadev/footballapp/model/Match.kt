package com.muzadev.footballapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Match(

        @SerializedName("intHomeShots")
        var intHomeShots: String? = null,

        @SerializedName("strSport")
        var strSport: String? = null,

        @SerializedName("strHomeLineupDefense")
        var strHomeLineupDefense: String? = null,

        @SerializedName("strAwayLineupSubstitutes")
        var strAwayLineupSubstitutes: String? = null,

        @SerializedName("idLeague")
        var idLeague: String? = null,

        @SerializedName("idSoccerXML")
        var idSoccerXML: String? = null,

        @SerializedName("strHomeLineupForward")
        var strHomeLineupForward: String? = null,

        @SerializedName("strHomeGoalDetails")
        var strHomeGoalDetails: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var strAwayLineupGoalkeeper: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var strAwayLineupMidfield: String? = null,

        @PrimaryKey
        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("intRound")
        var intRound: String? = null,

        @SerializedName("strHomeYellowCards")
        var strHomeYellowCards: String? = null,

        @SerializedName("idHomeTeam")
        var idHomeTeam: String? = null,

        @SerializedName("intHomeScore")
        var intHomeScore: String? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strAwayTeam")
        var strAwayTeam: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var strHomeLineupMidfield: String? = null,

        @SerializedName("strDate")
        var strDate: String? = null,

        @SerializedName("strHomeFormation")
        var strHomeFormation: String? = null,

        @SerializedName("idAwayTeam")
        var idAwayTeam: String? = null,

        @SerializedName("strAwayRedCards")
        var strAwayRedCards: String? = null,

        @SerializedName("intAwayShots")
        var intAwayShots: String? = null,

        @SerializedName("strFilename")
        var strFilename: String? = null,

        @SerializedName("strTime")
        var strTime: String? = null,

        @SerializedName("strAwayGoalDetails")
        var strAwayGoalDetails: String? = null,

        @SerializedName("strAwayLineupForward")
        var strAwayLineupForward: String? = null,

        @SerializedName("strLocked")
        var strLocked: String? = null,

        @SerializedName("strSeason")
        var strSeason: String? = null,

        @SerializedName("strHomeRedCards")
        var strHomeRedCards: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var strHomeLineupGoalkeeper: String? = null,

        @SerializedName("strHomeLineupSubstitutes")
        var strHomeLineupSubstitutes: String? = null,

        @SerializedName("strAwayFormation")
        var strAwayFormation: String? = null,

        @SerializedName("strEvent")
        var strEvent: String? = null,

        @SerializedName("strAwayYellowCards")
        var strAwayYellowCards: String? = null,

        @SerializedName("strAwayLineupDefense")
        var strAwayLineupDefense: String? = null,

        @SerializedName("strHomeTeam")
        var strHomeTeam: String? = null,

        @SerializedName("strLeague")
        var strLeague: String? = null,

        @SerializedName("intAwayScore")
        var intAwayScore: String? = null

) : RealmObject(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(intHomeShots)
        parcel.writeString(strSport)
        parcel.writeString(strHomeLineupDefense)
        parcel.writeString(strAwayLineupSubstitutes)
        parcel.writeString(idLeague)
        parcel.writeString(idSoccerXML)
        parcel.writeString(strHomeLineupForward)
        parcel.writeString(strHomeGoalDetails)
        parcel.writeString(strAwayLineupGoalkeeper)
        parcel.writeString(strAwayLineupMidfield)
        parcel.writeString(idEvent)
        parcel.writeString(intRound)
        parcel.writeString(strHomeYellowCards)
        parcel.writeString(idHomeTeam)
        parcel.writeString(intHomeScore)
        parcel.writeString(dateEvent)
        parcel.writeString(strAwayTeam)
        parcel.writeString(strHomeLineupMidfield)
        parcel.writeString(strDate)
        parcel.writeString(strHomeFormation)
        parcel.writeString(idAwayTeam)
        parcel.writeString(strAwayRedCards)
        parcel.writeString(intAwayShots)
        parcel.writeString(strFilename)
        parcel.writeString(strTime)
        parcel.writeString(strAwayGoalDetails)
        parcel.writeString(strAwayLineupForward)
        parcel.writeString(strLocked)
        parcel.writeString(strSeason)
        parcel.writeString(strHomeRedCards)
        parcel.writeString(strHomeLineupGoalkeeper)
        parcel.writeString(strHomeLineupSubstitutes)
        parcel.writeString(strAwayFormation)
        parcel.writeString(strEvent)
        parcel.writeString(strAwayYellowCards)
        parcel.writeString(strAwayLineupDefense)
        parcel.writeString(strHomeTeam)
        parcel.writeString(strLeague)
        parcel.writeString(intAwayScore)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Match> {
        override fun createFromParcel(parcel: Parcel): Match {
            return Match(parcel)
        }

        override fun newArray(size: Int): Array<Match?> {
            return arrayOfNulls(size)
        }
    }
}

data class MatchResponse(

        @SerializedName("events")
        val events: List<Match>
)