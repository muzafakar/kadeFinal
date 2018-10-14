package com.muzadev.footballapp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_tabbed.view.*

/**
 * Created by zulfakar on 13/10/18.
 * For educational purposes
 */
class MatchesFragment : Fragment() {
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val fragments = mutableListOf<Fragment>()
    private val titles = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapterArguments()
        viewPagerAdapter = ViewPagerAdapter(fragmentManager, fragments, titles)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_tabbed, container, false)

        v.matchViewPager.adapter = viewPagerAdapter
        v.matchesTab.setupWithViewPager(v.matchViewPager, true)
        viewPagerAdapter.notifyDataSetChanged()
        return v
    }

    private fun setupAdapterArguments() {
        with(fragments) {
            add(MatchNextFragment())
            add(MatchLastFragment())
        }

        with(titles) {
            add(getString(R.string.next))
            add(getString(R.string.last))
        }
    }

}