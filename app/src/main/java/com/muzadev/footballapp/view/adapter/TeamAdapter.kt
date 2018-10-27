package com.muzadev.footballapp.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Team
import com.muzadev.footballapp.view.fragment.TeamsFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team.view.*
import org.jetbrains.anko.AnkoLogger

/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
class TeamAdapter(private val context: Context,  private val listener: (Team) -> Unit) : RecyclerView.Adapter<TeamAdapter.ViewHolder>(), AnkoLogger {

    companion object {
        val list = mutableListOf<Team>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false))


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {

        holder.bindItem(list[postition], listener)
    }

    fun filter(text: String) {
        list.clear()
        if (text.isEmpty()) {
            list.addAll(TeamsFragment.teamListFull)
        } else {
            list.addAll(TeamsFragment.teamListFull.filter { it.strTeam!!.contains(text, true) })
        }

        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(team: Team, listener: (Team) -> Unit) {
            itemView.tvTeamName.text = team.strTeam
            Picasso.get().load(team.strTeamBadge).into(itemView.imgTeamBadge)
            itemView.setOnClickListener {
                listener(team)
            }
        }
    }
}
