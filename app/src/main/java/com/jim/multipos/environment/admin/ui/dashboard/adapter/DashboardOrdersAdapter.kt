package com.jim.multipos.environment.admin.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemDashboardOrdersBinding
import com.jim.multipos.core.adapter.BaseAdapter
import com.jim.multipos.core.BaseActions
import com.jim.multipos.core.adapter.BaseViewHolder
import com.jim.multipos.environment.admin.ui.dashboard.viewmodel.DashboardOrderItemViewModel


//class DashboardOrdersAdapter: BaseAdapter<String, BaseActions<String>, DashboardOrdersAdapter.OrderViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder{
//        return OrderViewHolder(AdminItemDashboardOrdersBinding.inflate(LayoutInflater.from(parent.context),parent, false))
//    }
//
//    inner class OrderViewHolder(binding: AdminItemDashboardOrdersBinding):
//            BaseViewHolder<String, BaseActions<String>>(binding.root){
//
//        var mViewBinding: AdminItemDashboardOrdersBinding? = null
//        var mViewModel: DashboardOrderItemViewModel? = null
//
//        init {
//            mViewBinding = binding
//        }
//
//        override fun onBind(item: String, listener: BaseActions<String>) {
//            mViewModel = DashboardOrderItemViewModel(item)
//            mViewBinding?.root?.setOnClickListener {  }
//            mViewBinding?.viewModel = mViewModel!!
//            mViewBinding?.executePendingBindings()
//        }
//
//    }
//}