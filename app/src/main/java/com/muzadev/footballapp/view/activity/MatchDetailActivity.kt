package com.muzadev.footballapp.view.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.muzadev.footballapp.R
import com.muzadev.footballapp.api.ApiRepo
import com.muzadev.footballapp.model.Match
import com.muzadev.footballapp.model.Team
import com.muzadev.footballapp.presenter.TeamPresenter
import com.muzadev.footballapp.presenter.interfaces.TeamView
import com.muzadev.footballapp.util.Const
import com.muzadev.footballapp.util.CoroutinesContextProvider
import com.muzadev.footballapp.util.MyFormatter
import com.squareup.picasso.Picasso
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), TeamView {


    private lateinit var match: Match
    private lateinit var presenter: TeamPresenter
    private lateinit var realm: Realm
    private var isFavorite: Boolean = false
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        realm = Realm.getDefaultInstance()
        match = intent.getParcelableExtra(Const.match) as Match
        matchDetailTB.title = match.strEvent
        matchDetailTB.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        setSupportActionBar(matchDetailTB)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = TeamPresenter(this, ApiRepo(), Gson(), CoroutinesContextProvider())

        bindData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorite, menu)
        menuItem = menu!!.getItem(0)
        getFavoriteState()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_favorite -> {
                if (!isFavorite) addToRealm() else removeFromRealm()

                isFavorite = !isFavorite
                setFavoriteState()
            }
        }
        return true
    }

    private fun addToRealm() {
        realm.beginTransaction()
        realm.copyToRealm(match)
        realm.commitTransaction()
        toast("${match.strEvent} is added to favorite")
    }

    private fun removeFromRealm() {
        realm.beginTransaction()
        val dMatch = realm.where(Match::class.java).equalTo("idEvent", match.idEvent).findFirst()
        dMatch?.deleteFromRealm()
        realm.commitTransaction()
        toast("${match.strEvent} is removed from favorite")
    }

    private fun setFavoriteState() {
        if (isFavorite) {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.favorite_on)
        } else {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.favorite_off)
        }
    }

    private fun getFavoriteState() {
        val favorite = realm.where(Match::class.java).equalTo("idEvent", match.idEvent).findAll()
        isFavorite = !favorite.isEmpty()
        setFavoriteState()
    }


    private fun bindData() {
        eventDate.text = MyFormatter.dateFormatter(match.strDate)
        eventTime.text = MyFormatter.timeFormatter(match.strTime)
//        HOME
        homeTeam.text = match.strHomeTeam.toString()
        homeScore.text = match.intHomeScore.toString()
        homeFormation.text = match.strHomeFormation.toString()
        homeGoals.text = parserGoal(match.strHomeGoalDetails.toString())
        homeShots.text = match.intHomeShots?.toString()
        homeGk.text = parserGoal(match.strHomeLineupGoalkeeper.toString())
        homeDef.text = parser(match.strHomeLineupDefense.toString())
        homeMdf.text = parser(match.strHomeLineupMidfield.toString())
        homeOfen.text = parser(match.strHomeLineupForward.toString())
        homeSubs.text = parser(match.strHomeLineupSubstitutes.toString())

//        AWAY
        awayTeam.text = match.strAwayTeam.toString()
        awayScore.text = match.intAwayScore.toString()
        awayFormation.text = match.strAwayFormation.toString()
        awayGoals.text = parserGoal(match.strAwayGoalDetails.toString())
        awayShots.text = match.intAwayShots?.toString()
        awayGk.text = parserGoal(match.strAwayLineupGoalkeeper.toString())
        awayDef.text = parser(match.strAwayLineupDefense.toString())
        awayMdf.text = parser(match.strAwayLineupMidfield.toString())
        awayOfen.text = parser(match.strAwayLineupForward.toString())
        awaySubs.text = parser(match.strAwayLineupSubstitutes.toString())

//        TO SHOW GRAB THE TEAM EMBLEM
        presenter.getTeam(match.idHomeTeam!!)
        presenter.getTeam(match.idAwayTeam!!)
//        getFavoriteState()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun parserGoal(input: String): String {
        return input.replace(";", "\n", false)
    }

    private fun parser(input: String): String {
        return input.replace("; ", "\n", false)
    }

    override fun showTeam(team: Team?) {
        if (team?.strTeam.equals(match.strHomeTeam)) {
            Picasso.get().load(team?.strTeamBadge).into(homeEmblem)
        } else if (team?.strTeam.equals(match.strAwayTeam)) {
            Picasso.get().load(team?.strTeamBadge).into(awayEmblem)
        }
    }

    override fun showTeams(teams: List<Team>?) {}

    override fun showLoading() {}

    override fun hideLoading() {}
}
