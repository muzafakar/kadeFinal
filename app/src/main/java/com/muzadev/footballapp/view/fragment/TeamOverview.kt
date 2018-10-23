package com.muzadev.footballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Team
import com.muzadev.footballapp.view.adapter.OverviewAdapter
import kotlinx.android.synthetic.main.fragment_rv.view.*

/**
 * Created by zulfakar on 21/10/18.
 * For educational purposes
 */
class TeamOverview : Fragment() {
    private lateinit var team: Team
    private lateinit var mView: View
    private lateinit var adapter: OverviewAdapter

    fun setTeam(team: Team) {
        this.team = team
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = OverviewAdapter(activity!!.applicationContext, team)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_rv, container, false)
        setupRecyclerView()
        return mView
    }

    private fun setupRecyclerView() {
        mView.rvCommon_.layoutManager = LinearLayoutManager(activity)
        mView.rvCommon_.adapter = adapter

        mView.pbCommon_.visibility = View.GONE
    }
}