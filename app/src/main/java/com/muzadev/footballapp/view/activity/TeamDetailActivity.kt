package com.muzadev.footballapp.view.activity

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Team
import com.muzadev.footballapp.util.Const
import com.muzadev.footballapp.view.adapter.TabLayoutAdapter
import com.muzadev.footballapp.view.fragment.TeamOverview
import com.muzadev.footballapp.view.fragment.TeamPlayers
import com.squareup.picasso.Picasso
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(), AnkoLogger {
    private lateinit var team: Team
    private lateinit var vpAdapter: TabLayoutAdapter
    private lateinit var realm: Realm
    private var isFavorite: Boolean = false
    private lateinit var menuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        realm = Realm.getDefaultInstance()
        info { "path ${realm.path}" }
        team = intent.getParcelableExtra(Const.team) as Team

        vpAdapter = TabLayoutAdapter(supportFragmentManager).apply {
            addFragment(TeamOverview().apply { setTeam(team) }, getString(R.string.overview))
            addFragment(TeamPlayers().apply { setTeamId(team.idTeam) }, getString(R.string.players))
        }
        setUpToolbarItem()
        setupViewPager()
    }

    private fun setupViewPager() {
        vpTeamMenu.adapter = vpAdapter
        tabTeamDetail.setupWithViewPager(vpTeamMenu, true)
    }

    private fun setUpToolbarItem() {
        tDetailToolbar.title = team.strTeam
        tDetailToolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
        setSupportActionBar(tDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Picasso.get().load(team.strTeamBadge).into(imgTeamBadge)
        tvTeamName.text = team.strTeam
        tvFormedYear.text = "${team.intFormedYear}"
        tvStadium.text = team.strStadium
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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
        realm.copyToRealm(team)
        realm.commitTransaction()
        toast("${team.strTeam} is added to favorite")
    }

    private fun removeFromRealm() {
        realm.beginTransaction()
        val dTeam = realm.where(Team::class.java).equalTo("idTeam", team.idTeam).findFirst()
        dTeam?.deleteFromRealm()
        realm.commitTransaction()
        toast("${team.strTeam} is removed from favorite")
    }

    private fun setFavoriteState() {
        if (isFavorite) {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.favorite_on)
        } else {
            menuItem.icon = ContextCompat.getDrawable(this, R.drawable.favorite_off)
        }
    }

    private fun getFavoriteState() {
        val favorite = realm.where(Team::class.java).equalTo("idTeam", team.idTeam).findAll()
        isFavorite = !favorite.isEmpty()
        setFavoriteState()
    }
}
