package com.muzadev.footballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.muzadev.footballapp.R
import com.muzadev.footballapp.api.ApiRepo
import com.muzadev.footballapp.model.Match
import com.muzadev.footballapp.presenter.MatchPresenter
import com.muzadev.footballapp.presenter.interfaces.MatchView
import com.muzadev.footballapp.util.Const
import com.muzadev.footballapp.util.CoroutinesContextProvider
import com.muzadev.footballapp.view.activity.MatchDetailActivity
import com.muzadev.footballapp.view.adapter.MatchAdapter
import kotlinx.android.synthetic.main.matches_layout.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */

class LastMatchFragment : Fragment(), MatchView, AdapterView.OnItemSelectedListener, AnkoLogger {

    //    view
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    //    adapter
    companion object {
        lateinit var rvAdapter: MatchAdapter
        lateinit var spinner: Spinner
    }

    private lateinit var spAdapter: ArrayAdapter<String>
    private lateinit var presenter: MatchPresenter

    //    list
    private val matchList = mutableListOf<Match>()
    private val leagueId = mutableListOf<String>()
    private val leagueName = mutableListOf<String>()

    //    single-valued
    private var currentLeagueId = "4328"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leagueName.addAll(ctx.resources.getStringArray(R.array.ALL_LEAGUE_NAMES))
        leagueId.addAll(ctx.resources.getStringArray(R.array.ALL_LEAGUE_IDS))

        spAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, leagueName)
        rvAdapter = MatchAdapter(activity!!.applicationContext, matchList, false) {
            ctx.startActivity(intentFor<MatchDetailActivity>(Const.match to it))
        }

        presenter = MatchPresenter(this, ApiRepo(), Gson(), CoroutinesContextProvider())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.matches_layout, container, false)
        progressBar = view.pbCommon

        recyclerView = view.rvCommonMatch
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = rvAdapter

        spinner = view.spCommon
        spinner.adapter = spAdapter
        spinner.onItemSelectedListener = this

        presenter.getLastMatch(currentLeagueId)

        return view
    }

    override fun showNextMatch(matches: List<Match>) {}

    override fun showLastMatch(matches: List<Match>) {
        matches.let {
            matchList.clear()
            matchList.addAll(it)

            MatchesFragment.list.clear()
            MatchesFragment.list.addAll(it)

            MatchAdapter.list.clear()
            MatchAdapter.list.addAll(it)
        }

        rvAdapter.notifyDataSetChanged()
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

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        LastMatchFragment.spinner.setSelection(position, true)
        currentLeagueId = leagueId[position]
        presenter.getLastMatch(currentLeagueId)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}