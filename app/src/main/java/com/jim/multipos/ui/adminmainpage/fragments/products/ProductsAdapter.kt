package com.jim.multipos.ui.adminmainpage.fragments.products

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemProductsBinding
import com.jim.multipos.ui.base.BaseAdapter
import com.jim.multipos.ui.base.BaseClickListener
import com.jim.multipos.ui.base.BaseViewHolder

class ProductsAdapter: BaseAdapter<String, BaseClickListener<String>, ProductsAdapter.ProductViewHolder>() {

    var lastPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(AdminItemProductsBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    inner class ProductViewHolder(binding: AdminItemProductsBinding):
            BaseViewHolder<String, BaseClickListener<String>>(binding.root) {

        var mViewBinding: AdminItemProductsBinding?=null
        var mViewModel: ProductsItemViewModel?=null

        init {
            mViewBinding = binding
        }

        override fun onBind(item: String, listener: BaseClickListener<String>) {
            mViewModel = ProductsItemViewModel(item)
            mViewBinding!!.root.setOnClickListener {
                listener.onItemClick(item)
                if(lastPos!=-1) notifyItemChanged(lastPos)
                lastPos = adapterPosition
                notifyItemChanged(lastPos)
            }

            if (lastPos == adapterPosition)
            mViewModel!!.isClicked.set(true)
            else
            mViewModel!!.isClicked.set(false)

            mViewBinding!!.viewModel = mViewModel
            mViewBinding!!.executePendingBindings()
        }

    }
}