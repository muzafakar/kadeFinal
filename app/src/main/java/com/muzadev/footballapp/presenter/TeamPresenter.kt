package com.muzadev.footballapp.presenter

import com.google.gson.Gson
import com.muzadev.footballapp.api.Api
import com.muzadev.footballapp.api.ApiRepo
import com.muzadev.footballapp.model.TeamResponse
import com.muzadev.footballapp.presenter.interfaces.TeamView
import com.muzadev.footballapp.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg


/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
class TeamPresenter(
        private val view: TeamView,
        private val apiRepo: ApiRepo,
        private val gson: Gson,
        private val context: CoroutinesContextProvider
) : AnkoLogger {
    fun getTeams(league: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepo.doRequest(Api.getTeams(league)),
                        TeamResponse::class.java).teams
            }

            view.showTeams(data.await())
            view.hideLoading()
        }
    }
}