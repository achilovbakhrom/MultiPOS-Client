package com.jim.multipos.customView.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.jim.multipos.R


class SpinnerAdapter constructor(val context:Context, val list: Array<String>): BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        view = convertView
        if(view == null)
            view = mInflater.inflate(R.layout.spinner_item, parent, false)

        var tvSpinnerItem = view!!.findViewById(R.id.tvSpinnerItem) as TextView
        tvSpinnerItem.text = list[position]

        return view
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }
}