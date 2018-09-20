package com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemDashboardOrdersBinding
import com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.viewmodel.DashboardOrderItemViewModel
import com.jim.multipos.core.BaseAdapter
import com.jim.multipos.core.BaseClickListener
import com.jim.multipos.core.BaseViewHolder


class DashboardOrdersAdapter: BaseAdapter<String, BaseClickListener<String>, DashboardOrdersAdapter.OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder{
        return OrderViewHolder(AdminItemDashboardOrdersBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    inner class OrderViewHolder(binding: AdminItemDashboardOrdersBinding):
            BaseViewHolder<String, BaseClickListener<String>>(binding.root){

        var mViewBinding: AdminItemDashboardOrdersBinding? = null
        var mViewModel: DashboardOrderItemViewModel? = null

        init {
            mViewBinding = binding
        }

        override fun onBind(item: String, listener: BaseClickListener<String>) {
            mViewModel = DashboardOrderItemViewModel(item)
            mViewBinding?.root?.setOnClickListener {  }
            mViewBinding?.viewModel = mViewModel!!
            mViewBinding?.executePendingBindings()
        }

    }
}