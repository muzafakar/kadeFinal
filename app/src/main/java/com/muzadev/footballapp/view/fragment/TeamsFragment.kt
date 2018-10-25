package com.muzadev.footballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.google.gson.Gson
import com.muzadev.footballapp.R
import com.muzadev.footballapp.api.ApiRepo
import com.muzadev.footballapp.model.Team
import com.muzadev.footballapp.presenter.TeamPresenter
import com.muzadev.footballapp.presenter.interfaces.TeamView
import com.muzadev.footballapp.util.Const
import com.muzadev.footballapp.util.CoroutinesContextProvider
import com.muzadev.footballapp.view.activity.TeamDetailActivity
import com.muzadev.footballapp.view.adapter.TeamAdapter
import kotlinx.android.synthetic.main.fragment_sp_rv.view.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.toast


/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class TeamsFragment : Fragment(), TeamView, AdapterView.OnItemSelectedListener, AnkoLogger {


    private lateinit var progressBar: ProgressBar
    private lateinit var rvAdapter: TeamAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var presenter: TeamPresenter
    private lateinit var spinner: Spinner
    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private lateinit var v: View

    private val leagues = mutableListOf<String>()
    private val teamList = mutableListOf<Team>()
    private var currentLeague = "English Premier League"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leagues.addAll(ctx.resources.getStringArray(R.array.ALL_LEAGUE_NAMES))
        spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, leagues)
        rvAdapter = TeamAdapter(activity!!.applicationContext, teamList) {
            // intent to teamDetail
            ctx.startActivity(intentFor<TeamDetailActivity>(Const.team to it))
        }
        presenter = TeamPresenter(this, ApiRepo(), Gson(), CoroutinesContextProvider())


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_sp_rv, container, false)
        v.tbCommon.subtitle = resources.getString(R.string.teams)
        (activity as AppCompatActivity).setSupportActionBar(v.tbCommon)
        progressBar = v.pbCommon
        setHasOptionsMenu(true)

        recyclerView = v.rvComon
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = rvAdapter

        spinner = v.spCommon
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = this


        //first  call
        presenter.getTeams(currentLeague)
        return v
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> {
                toast("search")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showTeams(teams: List<Team>?) {
        info { "showTeams" }
        teamList.clear()
        teams?.let { teamList.addAll(it) }
        rvAdapter.notifyDataSetChanged()
    }

    override fun showTeam(team: Team?) {}

    override fun showLoading() {
        info { "shwLading" }
        progressBar.isEnabled = true
        progressBar.visibility = View.VISIBLE
        progressBar.progress = 50
    }

    override fun hideLoading() {
        info { "hideLoading" }
        progressBar.isEnabled = false
        progressBar.visibility = View.GONE
        progressBar.progress = 0
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        currentLeague = spinnerAdapter.getItem(position).toString()
        presenter.getTeams(currentLeague)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

}