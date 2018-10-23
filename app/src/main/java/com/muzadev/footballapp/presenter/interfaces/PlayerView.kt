package com.muzadev.footballapp.presenter.interfaces

import com.muzadev.footballapp.model.Player

/**
 * Created by zulfakar on 21/10/18.
 * For educational purposes
 */
interface PlayerView : BaseView {
    fun showPlayerList(players: List<Player>)
    fun showPlayerDetail(player: Player)
}