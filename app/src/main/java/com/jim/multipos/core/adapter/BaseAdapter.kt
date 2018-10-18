package com.jim.multipos.core.adapter

import android.support.v7.widget.RecyclerView
import java.io.Serializable

abstract class BaseAdapter<T: Serializable, VH: BaseViewHolder<T>>: RecyclerView.Adapter<VH>() {

    internal var items = mutableListOf<T>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(items[position], position = position)
    }

    fun setItems(list: List<T>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

}


