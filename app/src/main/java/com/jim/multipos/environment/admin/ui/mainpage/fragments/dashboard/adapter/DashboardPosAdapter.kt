package com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemDashboardPosBinding
import com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.viewmodel.DashboardItemViewModel
import com.jim.multipos.core.BaseAdapter
import com.jim.multipos.core.BaseClickListener
import com.jim.multipos.core.BaseViewHolder

class DashboardPosAdapter(context: Context):
        BaseAdapter<String, BaseClickListener<String>, DashboardPosAdapter.PosViewHolder>(){

    var lastPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosViewHolder {
        return PosViewHolder(AdminItemDashboardPosBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    inner class PosViewHolder(binding: AdminItemDashboardPosBinding):
            BaseViewHolder<String, BaseClickListener<String>>(binding.root){

        var mViewBinding: AdminItemDashboardPosBinding? = null
        var mViewModel: DashboardItemViewModel? = null

        init {
            mViewBinding = binding
        }

        override fun onBind(item: String, listener: BaseClickListener<String>) {
            mViewModel = DashboardItemViewModel(item)

            mViewBinding?.root?.setOnClickListener {
                listener.onItemClick(item)
                if(lastPos!=-1) notifyItemChanged(lastPos)
                lastPos = adapterPosition
                notifyItemChanged(lastPos)
            }
            if (lastPos == adapterPosition)
                mViewModel?.isClicked?.set(true)
            else
                mViewModel?.isClicked?.set(false)
            mViewBinding?.viewModel= mViewModel
            mViewBinding?.executePendingBindings()
        }

    }
}