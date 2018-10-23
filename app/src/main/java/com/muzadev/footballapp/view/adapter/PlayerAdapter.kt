package com.muzadev.footballapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_player.view.*

/**
 * Created by zulfakar on 21/10/18.
 * For educational purposes
 */
class PlayerAdapter(private val context: Context, private val players: List<Player>, private val listener: (Player) -> Unit) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_player, parent, false))

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        holder.bindItem(players[postition], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(player: Player, listener: (Player) -> Unit) {
            itemView.tvPlayerName.text = player.strPlayer
            itemView.tvPlayerPosition.text = player.strPosition
            Picasso.get().load(player.strCutout).fit().into(itemView.imgPlayerCutOut)
            itemView.setOnClickListener {
                listener(player)
            }
        }
    }
}