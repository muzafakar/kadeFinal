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
class FavouritesFragment : Fragment() {
    private lateinit var v: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_tl_vp, container, false)
        v.tbCommon.subtitle = resources.getString(R.string.favourites)
        setupViewPager(v.viewPager)
        v.tabLayout.setupWithViewPager(v.viewPager, true)
        return v
    }

    private fun setupViewPager(viewpager: ViewPager) {
        val adapter = TabLayoutAdapter(childFragmentManager).apply {
            addFragment(FavouriteMatchFragment(), resources.getString(R.string.matches))
            addFragment(FavouriteTeamFragment(), resources.getString(R.string.teams))
        }
        viewpager.adapter = adapter
    }
}