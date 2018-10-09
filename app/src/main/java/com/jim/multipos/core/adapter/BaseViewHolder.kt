package com.jim.multipos.core.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: T, position: Int, isSelected: Boolean = false)
}

