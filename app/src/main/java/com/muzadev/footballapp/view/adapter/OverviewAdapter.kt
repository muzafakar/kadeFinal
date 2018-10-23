package com.muzadev.footballapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Team
import kotlinx.android.synthetic.main.item_overview.view.*

/**
 * Created by zulfakar on 23/10/18.
 * For educational purposes
 */
class OverviewAdapter(private val context: Context, private val team: Team) : RecyclerView.Adapter<OverviewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_overview, parent, false))

    override fun getItemCount() = 1

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        holder.bindItem(team)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(team: Team) {
            itemView.tvTeamDescription.text = team.strDescriptionEN
            itemView.tvStadiumDescription.text = team.strStadiumDescription
        }
    }
}