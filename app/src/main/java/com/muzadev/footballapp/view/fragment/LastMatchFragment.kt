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
import com.muzadev.footballapp.util.CoroutinesContextProvider
import com.muzadev.footballapp.view.adapter.MatchAdapter
import kotlinx.android.synthetic.main.fragment_sp_rv.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */

class LastMatchFragment : Fragment(), MatchView, AdapterView.OnItemSelectedListener, AnkoLogger {

    //    view
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner

    //    adapter
    private lateinit var rvAdapter: MatchAdapter
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
            toast("${it.strEvent}")
        }

        presenter = MatchPresenter(this, ApiRepo(), Gson(), CoroutinesContextProvider())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_sp_rv, container, false)
        progressBar = view.pbCommon

        recyclerView = view.rvComon
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
        matchList.clear()
        matchList.addAll(matches)
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
        currentLeagueId = leagueId[position]
        presenter.getLastMatch(currentLeagueId)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}