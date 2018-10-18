package com.jim.multipos.core.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import java.io.Serializable

abstract class BaseViewHolder<T: Serializable>(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: T, position: Int, isSelected: Boolean = false)
}

