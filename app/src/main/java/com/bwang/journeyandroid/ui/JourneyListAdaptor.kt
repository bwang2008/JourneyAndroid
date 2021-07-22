package com.bwang.journeyandroid.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bwang.journeyandroid.R
import com.bwang.journeyandroid.data.JourneyDTOItem
import retrofit2.Callback

class JourneyListAdaptor(val context: Context, var journeyList: List<JourneyDTOItem>,
                         val onItemClickListner: OnItemClickListner):
    RecyclerView.Adapter<JourneyListAdaptor.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var userId: TextView
        var title: TextView

        init {
            userId = itemView.findViewById(R.id.userId)
            title = itemView.findViewById(R.id.title)
        }

        fun initViewHolder(journeyDTOItem: JourneyDTOItem, clickListner: OnItemClickListner){
            userId.text = journeyDTOItem.userId.toString()
            title.text = journeyDTOItem.title
            itemView.setOnClickListener{
                clickListner.onItemClick(journeyDTOItem, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.journey_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.userId.text = journeyList[position].userId.toString()
//        holder.title.text = journeyList[position].title

        holder.initViewHolder(journeyList[position], onItemClickListner)
    }

    override fun getItemCount(): Int {
        return journeyList.size
    }

    interface OnItemClickListner{
        fun onItemClick(journeyDTOItem: JourneyDTOItem, position: Int)
    }
}

