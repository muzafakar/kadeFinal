package com.muzadev.footballapp.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.muzadev.footballapp.R
import com.muzadev.footballapp.fragment.FavouritesFragment
import com.muzadev.footballapp.fragment.MatchesFragment
import com.muzadev.footballapp.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val BACK_STACK = "root"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(MatchesFragment())
        setUpBottomNav()
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
//        supportFragmentManager.beginTransaction().replace(R.id.mainFrame, fragment).commit()
        supportFragmentManager.popBackStack(BACK_STACK, 0)
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, fragment)
                .addToBackStack(null)
                .commit()
    }
}
