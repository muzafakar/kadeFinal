package com.muzadev.footballapp.view.adapter

import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muzadev.footballapp.R
import com.muzadev.footballapp.model.Match
import com.muzadev.footballapp.util.MyFormatter
import com.muzadev.footballapp.view.fragment.MatchesFragment
import kotlinx.android.synthetic.main.item_match.view.*

/**
 * Created by zulfakar on 18/10/18.
 * For educational purposes
 */
class MatchAdapter(private val context: Context, private val matches: List<Match>, private val isNextMatch: Boolean = true, private val listener: (Match) -> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    companion object {
        val list = mutableListOf<Match>()
    }

    fun filter(text: String) {
        list.clear()
        if (text.isEmpty()) {
            list.addAll(MatchesFragment.list)
        } else {
            list.addAll(MatchesFragment.list.filter { it.strEvent!!.contains(text, true) })
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewTyoe: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, postition: Int) {
        holder.bindItem(list[postition], listener)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(match: Match, listener: (Match) -> Unit) {


            itemView.tvMatchDate.text = MyFormatter.dateFormatter(match.strDate)
            itemView.tvMatchTime.text = MyFormatter.timeFormatter(match.strTime)
            itemView.tvHomeTeam.text = match.strHomeTeam
            itemView.tvAwayTeam.text = match.strAwayTeam

            itemView.setOnClickListener {
                listener(match)
            }

            if (!isNextMatch) {
                itemView.imgReminder.visibility = View.GONE
                itemView.tvHomeScore.text = "${match.intHomeScore}"
                itemView.tvAwayScore.text = "${match.intAwayScore}"
            } else {
                itemView.imgReminder.setOnClickListener {
                    //                    Toast.makeText(context, "add to reminder", Toast.LENGTH_SHORT).show()
                    openCalendar(match.strDate)
                }
            }
        }

        private fun openCalendar(date: String?) {
            val startMillis: Long = 100
            val builder: Uri.Builder = CalendarContract.CONTENT_URI.buildUpon().appendPath(date)
            ContentUris.appendId(builder, startMillis)
            val intent = Intent(Intent.ACTION_VIEW).setData(builder.build())
            context.startActivity(intent)
        }
    }
}