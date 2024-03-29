package com.muzadev.footballapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Match
import kotlinx.android.synthetic.main.item_match.view.*

/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
class MatchAdapter(private val context: Context, private val matches: List<Match>, private val isAvailable: Boolean, private val listener: (Match) -> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))

    override fun getItemCount() = matches.size

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        holder.bindItem(matches[postition], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(match: Match, listener: (Match) -> Unit) {
            itemView.tvMatchDate.text = match.dateEvent
            itemView.tvMatchTime.text = match.strTime
            itemView.tvHomeTeam.text = match.strHomeTeam
            itemView.tvAwayTeam.text = match.strAwayTeam
            itemView.imgReminder.isEnabled = isAvailable
            itemView.setOnClickListener {
                listener(match)
            }
        }
    }
}