package com.muzadev.footballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Team
import com.muzadev.footballapp.util.Const
import com.muzadev.footballapp.view.activity.TeamDetailActivity
import com.muzadev.footballapp.view.adapter.TeamAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_rv.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class FavouriteTeamFragment : Fragment() {
    private lateinit var realm: Realm
    private lateinit var v: View
    private lateinit var adapter: TeamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
        adapter = TeamAdapter(activity!!.applicationContext) {
            ctx.startActivity(intentFor<TeamDetailActivity>(Const.team to it))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_rv, container, false)
        v.pbCommon_.visibility = View.GONE
        setupRecyclerView()
        getFavorites()
        return v
    }

    private fun getFavorites() {
        val favorites = realm.where(Team::class.java).findAll()
        TeamAdapter.list.clear()
        if (favorites.isNotEmpty()) {
            TeamAdapter.list.addAll(favorites)
            v.tvNoFav.visibility = View.GONE
        } else {
            v.tvNoFav.visibility = View.VISIBLE
        }
        adapter.notifyDataSetChanged()
    }

    private fun setupRecyclerView() {
        v.rvCommon_.layoutManager = LinearLayoutManager(activity)
        v.rvCommon_.adapter = adapter
    }
}