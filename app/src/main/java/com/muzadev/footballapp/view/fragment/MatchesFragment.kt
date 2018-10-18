package com.muzadev.footballapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.view.adapter.TabLayoutAdapter
import kotlinx.android.synthetic.main.fragment_tl_vp.view.*

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class MatchesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tl_vp, container, false)
        setupViewPager(view.viewPager)
        view.tabLayout.setupWithViewPager(view.viewPager, true)
        return view
    }

    fun setupViewPager(viewpager: ViewPager) {
        val adapter = TabLayoutAdapter(childFragmentManager)
        adapter.addFragment(NextMatchFragment(), resources.getString(R.string.next))
        adapter.addFragment(LastMatchFragment(), resources.getString(R.string.last))
        viewpager.adapter = adapter
    }
}