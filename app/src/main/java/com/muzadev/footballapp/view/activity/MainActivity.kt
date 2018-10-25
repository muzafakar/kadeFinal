package com.muzadev.footballapp.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.muzadev.footballapp.R
import com.muzadev.footballapp.view.fragment.FavouritesFragment
import com.muzadev.footballapp.view.fragment.MatchesFragment
import com.muzadev.footballapp.view.fragment.TeamsFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.design.snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        supportActionBar?.title = getString(R.string.app_name)
        setUpBottomNav()
        replaceFragment(MatchesFragment())

        Realm.init(this)
    }

    private fun setUpBottomNav() {
        mainBottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_mathes -> {
                    replaceFragment(MatchesFragment())
                    true
                }
                R.id.bottom_teams -> {
                    replaceFragment(TeamsFragment())
                    true
                }
                R.id.bottom_favourties -> {
                    replaceFragment(FavouritesFragment())
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack("#", 0)
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .addToBackStack("#")
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> {
                snackbar(mainRoot, "Search")
            }
        }
        return true
    }
}
