package com.jim.multipos.customView.recyclerView.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.jim.multipos.R
import java.io.Serializable

abstract class BaseAdapter<T: Serializable>(private val viewHolder: BaseViewHolder<T>): RecyclerView.Adapter<BaseViewHolder<T>>() {

    internal var items: MutableList<T?> = mutableListOf()
    var loadingProgressId: Int = R.layout.loading_progress_layout

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.adapter = this
        holder.onBind(items[position], position = position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        return if (viewType == 0) {
            viewHolder.newInstance(parent.context, parent)
        } else {
            ProgressViewHolder<T>(View(parent.context)).newInstance(parent.context, parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) {
            -1
        } else {
            0
        }
    }

    /**
     *  Completely replace items with coming list items
     */
    fun setItems(list: List<T?>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    /**
     *  Adds list to the end of items
     */
    fun addItems(list: List<T?>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    /**
     *  Inserts item list by given position
     */
    fun addItemsAt(list: List<T?>, at: Int) {
        if (at > items.size - 1) {
            throw Exception("Inserting position is greater than items last position!!!")
        }
        items.addAll(at, list)
        notifyDataSetChanged()
    }

    /**
     *  Adds an item at the end of items list
     */
    fun addItem(item: T?) {
        items.add(item)
        notifyDataSetChanged()
    }

    /**
     *  Removes item from the items list
     */
    fun removeItem(item: T?) {
        val pos = items.indexOf(item)
        items.remove(item)
        notifyItemRemoved(pos)
    }

    /**
     *  Removes given items list from the items list
     */
    fun removeItems(items: List<T?>) {
        for (item in items) {
            removeItem(item)
        }
    }

    /**
     *  Clears items list
     */
    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

}


