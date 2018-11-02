package com.jim.multipos.customView.recyclerView.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.View
import android.view.ViewGroup
import java.io.Serializable

abstract class BaseViewHolder<T: Serializable>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var adapter: BaseAdapter<T>? = null
    abstract fun onBind(item: T?, position: Int, isSelected: Boolean = false, mode: SelectionMode = SelectionMode.SINGLE)
    abstract fun newInstance(context: Context, parent: ViewGroup): BaseViewHolder<T>
}

