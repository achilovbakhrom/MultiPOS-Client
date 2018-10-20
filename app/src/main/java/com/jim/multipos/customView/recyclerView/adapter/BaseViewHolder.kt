package com.jim.multipos.customView.recyclerView.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.View
import java.io.Serializable

abstract class BaseViewHolder<T: Serializable>(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    var adapter: BaseAdapter<T>? = null

    abstract fun onBind(item: T?, position: Int, isSelected: Boolean = false)

    abstract fun newInstance(context: Context): BaseViewHolder<T>

    internal open fun onBeginSelection(lastSelection: Int) {}
    internal open fun onSingleModeItemSelected(item: T?, position: Int) {}
    internal open fun onMultipleModeItemSelected(items: List<T?>, positions: List<Int>) {}
    internal open fun onItemLongClicked(item: T?, position: Int) {}

}

