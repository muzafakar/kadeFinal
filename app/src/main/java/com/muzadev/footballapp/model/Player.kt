package com.muzadev.footballapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by zulfakar on 21/10/18.
 * For educational purposes
 */
data class Player(

        @SerializedName("strPlayer")
        val strPlayer: String? = null,

        @SerializedName("strDescriptionES")
        val strDescriptionES: Any? = null,

        @SerializedName("dateBorn")
        val dateBorn: String? = null,

        @SerializedName("strNationality")
        val strNationality: String? = null,

        @SerializedName("strBanner")
        val strBanner: Any? = null,

        @SerializedName("strSport")
        val strSport: String? = null,

        @SerializedName("strWeight")
        val strWeight: String? = null,

        @SerializedName("strDescriptionCN")
        val strDescriptionCN: Any? = null,

        @SerializedName("strDescriptionIT")
        val strDescriptionIT: Any? = null,

        @SerializedName("strInstagram")
        val strInstagram: String? = null,

        @SerializedName("idTeam")
        val idTeam: String? = null,

        @SerializedName("strDescriptionEN")
        val strDescriptionEN: String? = null,

        @SerializedName("strBirthLocation")
        val strBirthLocation: String? = null,

        @SerializedName("strWebsite")
        val strWebsite: String? = null,

        @SerializedName("strHeight")
        val strHeight: String? = null,

        @SerializedName("strPosition")
        val strPosition: String? = null,

        @SerializedName("strYoutube")
        val strYoutube: String? = null,

        @SerializedName("strDescriptionIL")
        val strDescriptionIL: Any? = null,

        @SerializedName("strCutout")
        val strCutout: String? = null,

        @SerializedName("idPlayerManager")
        val idPlayerManager: String? = null,

        @SerializedName("strLocked")
        val strLocked: String? = null,

        @SerializedName("intLoved")
        val intLoved: String? = null,

        @SerializedName("idSoccerXML")
        val idSoccerXML: String? = null,

        @SerializedName("strTeam")
        val strTeam: String? = null,

        @SerializedName("intSoccerXMLTeamID")
        val intSoccerXMLTeamID: String? = null,

        @SerializedName("strDescriptionHU")
        val strDescriptionHU: Any? = null,

        @SerializedName("strTwitter")
        val strTwitter: String? = null,

        @SerializedName("strSigning")
        val strSigning: String? = null,

        @SerializedName("strGender")
        val strGender: String? = null,

        @SerializedName("strDescriptionSE")
        val strDescriptionSE: Any? = null,

        @SerializedName("strDescriptionJP")
        val strDescriptionJP: Any? = null,

        @SerializedName("strFanart1")
        val strFanart1: String? = null,

        @SerializedName("strDescriptionFR")
        val strDescriptionFR: Any? = null,

        @SerializedName("strFanart2")
        val strFanart2: String? = null,

        @SerializedName("strFanart3")
        val strFanart3: String? = null,

        @SerializedName("strFacebook")
        val strFacebook: String? = null,

        @SerializedName("strFanart4")
        val strFanart4: String? = null,

        @SerializedName("strCollege")
        val strCollege: Any? = null,

        @SerializedName("idPlayer")
        val idPlayer: String? = null,

        @SerializedName("strDescriptionNL")
        val strDescriptionNL: Any? = null,

        @SerializedName("strDescriptionRU")
        val strDescriptionRU: Any? = null,

        @SerializedName("strDescriptionPT")
        val strDescriptionPT: Any? = null,

        @SerializedName("strDescriptionDE")
        val strDescriptionDE: Any? = null,

        @SerializedName("strDescriptionNO")
        val strDescriptionNO: Any? = null,

        @SerializedName("strThumb")
        val strThumb: String? = null,

        @SerializedName("strWage")
        val strWage: String? = null,

        @SerializedName("dateSigned")
        val dateSigned: String? = null,

        @SerializedName("strDescriptionPL")
        val strDescriptionPL: Any? = null
) : Serializable

data class PlayerResponse(

        @SerializedName("player")
        val player: List<Player>
)