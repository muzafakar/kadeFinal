package com.muzadev.footballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Match
import com.muzadev.footballapp.util.Const
import com.muzadev.footballapp.view.activity.MatchDetailActivity
import com.muzadev.footballapp.view.adapter.MatchAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.fragment_rv.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class FavouriteMatchFragment : Fragment() {
    private lateinit var realm: Realm
    private lateinit var v: View
    private lateinit var adapter: MatchAdapter
    private val matches = mutableListOf<Match>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
        adapter = MatchAdapter(activity!!.applicationContext, matches) {
            ctx.startActivity(intentFor<MatchDetailActivity>(Const.match to it))
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
        val favorites = realm.where(Match::class.java).findAll()
        MatchAdapter.list.clear()
        if (favorites.isNotEmpty()) {
            MatchAdapter.list.addAll(favorites)
            adapter.notifyDataSetChanged()
            v.tvNoFav.visibility = View.GONE
        } else {
            v.tvNoFav.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView() {
        v.rvCommon_.layoutManager = LinearLayoutManager(activity)
        v.rvCommon_.adapter = adapter
    }
}