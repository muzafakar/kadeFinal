package com.muzadev.footballapp.presenter

import com.google.gson.Gson
import com.muzadev.footballapp.api.Api
import com.muzadev.footballapp.api.ApiRepo
import com.muzadev.footballapp.model.MatchResponse
import com.muzadev.footballapp.presenter.interfaces.MatchView
import com.muzadev.footballapp.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

/**
 * Created by zulfakar on 20/10/18.
 * For educational purposes
 */
class MatchPresenter(
        private val view: MatchView,
        private val apiRepo: ApiRepo,
        private val gson: Gson,
        private val context: CoroutinesContextProvider
) {

    fun getNextMatch(leagueId: String) {
        async(context.main) {
            view.showLoading()
            val data = bg {
                gson.fromJson(apiRepo.doRequest(Api.getNextMatches(leagueId))
                        , MatchResponse::class.java).events
            }

            view.showNextMatch(data.await())
            view.hideLoading()
        }
    }

    fun getLastMatch(leagueId: String) {
        async(context.main) {
            view.showLoading()
            val data = bg {
                gson.fromJson(apiRepo.doRequest(Api.getLastMatch(leagueId))
                        , MatchResponse::class.java).events
            }

            view.showLastMatch(data.await())
            view.hideLoading()
        }
    }

}