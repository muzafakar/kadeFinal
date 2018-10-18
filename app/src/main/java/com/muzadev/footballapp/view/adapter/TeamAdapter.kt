package com.muzadev.footballapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Team
import kotlinx.android.synthetic.main.item_team.view.*

/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
class TeamAdapter(private val context: Context, private val teams: List<Team>, private val listener: (Team) -> Unit) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        holder.bindItem(teams[postition], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(team: Team, listener: (Team) -> Unit) {
            itemView.tvTeamName.text = team.strTeam
            Glide.with(context).load(team.strTeamBadge).into(itemView.imgTeamBadge)
            itemView.setOnClickListener {
                listener(team)
            }
        }
    }
}