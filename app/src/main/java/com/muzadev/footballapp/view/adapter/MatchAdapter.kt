package com.muzadev.footballapp.view.adapter

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
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
import org.jetbrains.anko.toast
import java.util.*


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
                    openCalendar(match)
                }
            }
        }

        @SuppressLint("MissingPermission")
        private fun openCalendar(match: Match) {
            val resolver = context.contentResolver
            val values = ContentValues()


            values.put(CalendarContract.Events.TITLE, match.strEvent)
            values.put(CalendarContract.Events.DESCRIPTION, "Added from Football App")
            values.put(CalendarContract.Events.CALENDAR_ID, 1)
            values.put(CalendarContract.Events.DTSTART, Calendar.getInstance().timeInMillis)
            values.put(CalendarContract.Events.DURATION, Calendar.getInstance().timeInMillis + 60 * 60 * 1000)
            values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance().timeZone.id)



            resolver.insert(CalendarContract.Events.CONTENT_URI, values)
            context.toast("${match.strEvent} added to calendar")


        }
    }
}
