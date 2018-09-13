package com.jim.multipos.ui.adminmainpage.fragments.establishment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemEstablishmentBinding
import com.jim.multipos.ui.adminmainpage.fragments.establishment.viewmodel.EstablishmentItemViewModel
import com.jim.multipos.ui.base.BaseAdapter
import com.jim.multipos.ui.base.BaseClickListener
import com.jim.multipos.ui.base.BaseViewHolder

class EstablishmentAdapter:BaseAdapter<String, BaseClickListener<String>, EstablishmentAdapter.EstablishmentViewHolder>() {

    var lastPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstablishmentViewHolder {
        return EstablishmentViewHolder(AdminItemEstablishmentBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    inner class EstablishmentViewHolder(binding:AdminItemEstablishmentBinding):
            BaseViewHolder<String, BaseClickListener<String>>(binding.root) {

        var mViewBinding: AdminItemEstablishmentBinding?=null
        var mViewModel: EstablishmentItemViewModel?=null

        init {
            mViewBinding = binding
        }

        override fun onBind(item: String, listener: BaseClickListener<String>) {
            mViewModel = EstablishmentItemViewModel(item)
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