package com.bwang.journeyandroid.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bwang.journeyandroid.R
import com.bwang.journeyandroid.data.JourneyDTOItem

class JourneyListAdaptor(val context: Context, val journeyList: List<JourneyDTOItem>):
    RecyclerView.Adapter<JourneyListAdaptor.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var userId: TextView
        var title: TextView

        init {
            userId = itemView.findViewById(R.id.userId)
            title = itemView.findViewById(R.id.title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.journey_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = journeyList[position].userId.toString()
        holder.title.text = journeyList[position].title
    }

    override fun getItemCount(): Int {
        return journeyList.size
    }
}