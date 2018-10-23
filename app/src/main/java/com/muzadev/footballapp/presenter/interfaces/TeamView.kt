package com.muzadev.footballapp.presenter.interfaces

import com.muzadev.footballapp.model.Team

/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
interface TeamView : BaseView {
    fun showTeams(teams: List<Team>?)
    fun showTeam(team: Team?)
}