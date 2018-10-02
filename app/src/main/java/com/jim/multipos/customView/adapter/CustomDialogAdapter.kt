package com.jim.multipos.customView.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import com.jim.multipos.R


class CustomDialogAdapter(var list: List<String>, var context: Context): RecyclerView.Adapter<CustomDialogAdapter.ViewHolder>() {

    var lastPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_dialog_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(lastPos == position) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorNavy))
            holder.ivCheck?.visibility = VISIBLE
            holder.tvItemName?.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        }
        else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
            holder.ivCheck?.visibility = INVISIBLE
            holder.tvItemName?.setTextColor(ContextCompat.getColor(context, R.color.colorTitle))
        }

        holder.tvItemName?.text = list[position]
        holder.itemView.setOnClickListener {
            lastPos = position
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var tvItemName: TextView?=null
        var ivCheck: ImageView?=null

        init {
            tvItemName = view.findViewById(R.id.tvItemName)
            ivCheck = view.findViewById(R.id.ivCheck)
        }
    }
}