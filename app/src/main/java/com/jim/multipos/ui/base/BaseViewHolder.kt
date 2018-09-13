package com.jim.multipos.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<T, L>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: T, listener: L)
}