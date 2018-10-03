package com.jim.multipos.environment.admin.ui.entities.productclass

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemProductClassBinding
import com.jim.multipos.core.BaseAdapter
import com.jim.multipos.core.BaseClickListener
import com.jim.multipos.core.BaseViewHolder

class ProductClassAdapter: BaseAdapter<String, BaseClickListener<String>, ProductClassAdapter.ProductClassViewHolder>() {

    var lastPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductClassViewHolder {
        return ProductClassViewHolder(AdminItemProductClassBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    inner class ProductClassViewHolder(binding: AdminItemProductClassBinding):
            BaseViewHolder<String, BaseClickListener<String>>(binding.root) {

        var mViewBinding: AdminItemProductClassBinding? = null
        var mViewModel: ProductClassItemViewModel? = null

        init {
            mViewBinding = binding
        }

        override fun onBind(item: String, listener: BaseClickListener<String>) {
            mViewModel = ProductClassItemViewModel(item)
            mViewBinding!!.root.setOnClickListener {
                listener.onItemClick(item)
                if(lastPos!=-1) notifyItemChanged(lastPos)
                lastPos = adapterPosition
                notifyItemChanged(lastPos)
            }

            if (lastPos == adapterPosition)
                mViewModel?.isClicked?.set(true)
            else
                mViewModel?.isClicked?.set(false)

            mViewBinding?.viewModel = mViewModel!!
            mViewBinding?.executePendingBindings()
        }
    }
}