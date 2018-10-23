package com.muzadev.footballapp.presenter

import com.google.gson.Gson
import com.muzadev.footballapp.api.Api
import com.muzadev.footballapp.api.ApiRepo
import com.muzadev.footballapp.model.PlayerResponse
import com.muzadev.footballapp.presenter.interfaces.PlayerView
import com.muzadev.footballapp.util.CoroutinesContextProvider
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.coroutines.experimental.bg


/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
class PlayerPresenter(
        private val view: PlayerView,
        private val apiRepo: ApiRepo,
        private val gson: Gson,
        private val context: CoroutinesContextProvider
) : AnkoLogger {
    fun getPlayerList(teamId: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepo.doRequest(Api.getPlayerList(teamId)),
                        PlayerResponse::class.java).player
            }

            view.showPlayerList(data.await())
            view.hideLoading()
        }
    }

    fun getPlayerDetail(playerId: String) {
        view.showLoading()
        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepo.doRequest(Api.getPlayerList(playerId)),
                        PlayerResponse::class.java).player[0]
            }

            view.showPlayerDetail(data.await())
            view.hideLoading()
        }
    }
}