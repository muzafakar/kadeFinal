package com.muzadev.footballapp.view.activity

import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.design.snackbar

class TeamDetailActivity : AppCompatActivity() {
    private lateinit var team: Team
    private lateinit var vpAdapter: TabLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        team = intent.getSerializableExtra(Const.team) as Team
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
        tDetailToolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back)
        setSupportActionBar(tDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

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
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_favorite -> {
                snackbar(teamDetailRoot, "Favorite")
            }
        }
        return true
    }
}
