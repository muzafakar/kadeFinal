package com.muzadev.footballapp.view.fragment

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.*
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Match
import com.muzadev.footballapp.view.activity.MainActivity
import com.muzadev.footballapp.view.adapter.MatchAdapter
import com.muzadev.footballapp.view.adapter.TabLayoutAdapter
import kotlinx.android.synthetic.main.fragment_tl_vp.view.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by zulfakar on 16/10/18.
 * For educational purposes
 */
class MatchesFragment : Fragment(), AnkoLogger, SearchView.OnQueryTextListener {
    private lateinit var v: View

    companion object {
        val list = mutableListOf<Match>()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        MatchAdapter.list.clear()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fragment_tl_vp, container, false)
        v.tbCommon.subtitle = resources.getString(R.string.matches)
        (activity as AppCompatActivity).setSupportActionBar(v.tbCommon)

        setupViewPager()
        v.tabLayout.setupWithViewPager(v.viewPager, true)
        return v
    }

    private fun setupViewPager() {
        val adapter = TabLayoutAdapter(childFragmentManager)
        adapter.addFragment(NextMatchFragment(), resources.getString(R.string.next))
        adapter.addFragment(LastMatchFragment(), resources.getString(R.string.last))
        v.viewPager.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_search, menu)
        val ac = activity as MainActivity
        val searchManager = ac.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val sv = menu?.findItem(R.id.action_search)?.actionView as SearchView
        sv.isIconified = true
        sv.setSearchableInfo(searchManager.getSearchableInfo(ac.componentName))

        sv.setOnQueryTextListener(this)


        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(text: String?): Boolean {
        if (v.viewPager.currentItem == 0) {
            NextMatchFragment.rvAdapter.filter(text!!.toLowerCase())
        } else if (v.viewPager.currentItem == 1) {
            LastMatchFragment.rvAdapter.filter(text!!.toLowerCase())
        }
        return false
    }
}