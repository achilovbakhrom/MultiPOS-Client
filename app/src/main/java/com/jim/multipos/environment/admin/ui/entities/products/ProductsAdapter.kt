package com.jim.multipos.environment.admin.ui.entities.products

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemProductsBinding
import com.jim.multipos.core.adapter.BaseAdapter
import com.jim.multipos.core.BaseActions
import com.jim.multipos.core.adapter.BaseViewHolder

//class ProductsAdapter: BaseAdapter<String, BaseActions<String>, ProductsAdapter.ProductViewHolder>() {
//
//    var lastPos = -1
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        return ProductViewHolder(AdminItemProductsBinding.inflate(LayoutInflater.from(parent.context),parent, false))
//    }
//
//    inner class ProductViewHolder(binding: AdminItemProductsBinding):
//            BaseViewHolder<String, BaseActions<String>>(binding.root) {
//
//        var mViewBinding: AdminItemProductsBinding?=null
//        var mViewModel: ProductsItemViewModel?=null
//
//        init {
//            mViewBinding = binding
//        }
//
//        override fun onBind(item: String, listener: BaseActions<String>) {
//            mViewModel = ProductsItemViewModel(item)
//            mViewBinding?.root?.setOnClickListener {
//                listener.onItemClick(item)
//                if(lastPos!=-1) notifyItemChanged(lastPos)
//                lastPos = adapterPosition
//                notifyItemChanged(lastPos)
//            }
//
//            if (lastPos == adapterPosition)
//            mViewModel?.isClicked?.set(true)
//            else
//            mViewModel?.isClicked?.set(false)
//
//            mViewBinding?.viewModel = mViewModel
//            mViewBinding?.executePendingBindings()
//        }
//
//    }
//}