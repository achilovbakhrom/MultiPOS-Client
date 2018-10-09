package com.jim.multipos.environment.admin.ui.entities.productclass.productClassList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jim.multipos.R
import com.jim.multipos.core.adapter.BaseViewHolder
import com.jim.multipos.core.adapter.SelectableAdapter
import com.jim.multipos.environment.admin.model.ProductClass

class ProductClassListAdapter(val context: Context): SelectableAdapter<ProductClass, ProductClassListAdapter.ProductClassViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductClassViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_class_list_item, parent, false)
        return ProductClassViewHolder(view)
    }

    inner class ProductClassViewHolder(view: View?): BaseViewHolder<ProductClass>(view) {
        var productClassName: TextView
        var productClassDescription: TextView

        init {
            productClassName = itemView.findViewById(R.id.tvProductClassName)
            productClassDescription = itemView.findViewById(R.id.tvProductClassDescription)
        }

        override fun onBind(item: ProductClass, position: Int, isSelected: Boolean) {
            productClassName.text = item.name
            productClassName.text = item.description
            if (isSelected) {
                itemView.setBackgroundResource(R.color.colorDarkBlue)
            } else {
                itemView.setBackgroundResource(R.color.colorWhite)
            }
        }
    }


}