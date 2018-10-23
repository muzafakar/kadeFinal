package com.muzadev.footballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.muzadev.footballapp.R
import com.muzadev.footballapp.api.ApiRepo
import com.muzadev.footballapp.model.Player
import com.muzadev.footballapp.presenter.PlayerPresenter
import com.muzadev.footballapp.presenter.interfaces.PlayerView
import com.muzadev.footballapp.util.Const
import com.muzadev.footballapp.util.CoroutinesContextProvider
import com.muzadev.footballapp.view.activity.PlayerDetailActivity
import com.muzadev.footballapp.view.adapter.PlayerAdapter
import kotlinx.android.synthetic.main.fragment_rv.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor

/**
 * Created by zulfakar on 21/10/18.
 * For educational purposes
 */
class TeamPlayers : Fragment(), PlayerView {
    private lateinit var teamId: String
    private lateinit var presenter: PlayerPresenter
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private val playerList = mutableListOf<Player>()

    fun setTeamId(teamId: String?) {
        this.teamId = teamId!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PlayerPresenter(this, ApiRepo(), Gson(), CoroutinesContextProvider())
        playerAdapter = PlayerAdapter(activity!!.applicationContext, playerList) {
            ctx.startActivity(intentFor<PlayerDetailActivity>(Const.player to it))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_rv, container, false)
        progressBar = view.pbCommon_

        recyclerView = view.rvCommon_
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = playerAdapter

        presenter.getPlayerList(teamId)
        return view
    }

    override fun showPlayerList(players: List<Player>) {
        playerList.clear()
        playerList.addAll(players)
        playerAdapter.notifyDataSetChanged()
    }

    override fun showPlayerDetail(player: Player) {/*not implemented on this class*/
    }

    override fun showLoading() {
        progressBar.isEnabled = true
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 50
    }

    override fun hideLoading() {
        progressBar.isEnabled = false
        progressBar.visibility = View.GONE
        progressBar.progress = 0
    }

}