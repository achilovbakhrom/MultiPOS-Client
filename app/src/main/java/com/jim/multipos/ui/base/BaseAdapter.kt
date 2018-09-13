package com.jim.multipos.ui.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


abstract class BaseAdapter<T, L, VH:BaseViewHolder<T,L>>: RecyclerView.Adapter<VH>() {

    private var items: ArrayList<T>? = null
    var listener: L?=null

    init {
        items = ArrayList()
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun getItemCount(): Int {
        return if (items != null) items!!.size else 0
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(items!![position], listener!!)
    }

    fun setItems(list: List<T>){
        items!!.clear()
        items!!.addAll(list)
        notifyDataSetChanged()
    }

}