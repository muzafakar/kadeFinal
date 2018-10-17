package com.muzadev.footballapp.activity.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.activity.adapter.TabLayoutAdapter
import kotlinx.android.synthetic.main.fragment_matches.view.*

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class MatchesFragment : Fragment() {
    private val fragment = mutableListOf<Fragment>()
    private val title = mutableListOf<String>()
    private lateinit var tabLayoutAdapter: TabLayoutAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(fragment) {
            add(NextMatchFragment())
            add(LastMatchFragment())
        }

        with(title) {
            add(resources.getString(R.string.next))
            add(resources.getString(R.string.last))
        }

        tabLayoutAdapter = TabLayoutAdapter(activity?.supportFragmentManager, fragment, title)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_matches, container, false)
        view.vpMatches.adapter = tabLayoutAdapter
        view.tabMatches.setupWithViewPager(view.vpMatches, true)
        return view
    }
}