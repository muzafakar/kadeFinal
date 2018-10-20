package com.muzadev.footballapp.presenter.interfaces

import com.muzadev.footballapp.model.Match

/**
 * Created by zulfakar on 20/10/18.
 * For educational purposes
 */
interface MatchView : BaseView {
    fun showNextMatch(matches: List<Match>)
    fun showLastMatch(matches: List<Match>)
}