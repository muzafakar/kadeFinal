package com.muzadev.footballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.muzadev.footballapp.R
import com.muzadev.footballapp.api.Api
import com.muzadev.footballapp.model.Team
import com.muzadev.footballapp.presenter.TeamPresenter
import com.muzadev.footballapp.presenter.TeamView
import com.muzadev.footballapp.view.adapter.TeamAdapter
import kotlinx.android.synthetic.main.fragment_rv.view.*

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class TeamsFragment : Fragment(), TeamView {
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: TeamAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: TeamPresenter
    private val teamList = mutableListOf<Team>()
    private val api = Api.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TeamAdapter(activity!!.applicationContext, teamList) {
            // intent to teamDetail
        }
        presenter = TeamPresenter(this)
        presenter.getTeams()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sp_rv, container, false)
        progressBar = view.progressBar
        recyclerView = view.recyclerView
        return view
    }

    override fun showTeams(teams: List<Team>) {
        teamList.addAll(teams)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    override fun showLoading() {
        progressBar.isEnabled = true
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 50
    }

    override fun hideLoading() {
        progressBar.isEnabled = false
        progressBar.visibility = View.INVISIBLE
        progressBar.progress = 0
    }
}