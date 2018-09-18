package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemEstablishmentPosBinding
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.viewmodel.EstablishmentPosItemViewModel
import com.jim.multipos.core.BaseAdapter
import com.jim.multipos.core.BaseClickListener
import com.jim.multipos.core.BaseViewHolder

class EstablishmentPosAdapter: BaseAdapter<String, BaseClickListener<String>, EstablishmentPosAdapter.ViewHolder>() {

    var lastPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AdminItemEstablishmentPosBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    inner class ViewHolder(binding: AdminItemEstablishmentPosBinding): BaseViewHolder<String, BaseClickListener<String>>(binding.root) {

        var mViewBinding: AdminItemEstablishmentPosBinding? = null
        var mViewModel: EstablishmentPosItemViewModel? = null

        init {
            mViewBinding = binding
        }

        override fun onBind(item: String, listener: BaseClickListener<String>) {
            mViewModel = EstablishmentPosItemViewModel(item)
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