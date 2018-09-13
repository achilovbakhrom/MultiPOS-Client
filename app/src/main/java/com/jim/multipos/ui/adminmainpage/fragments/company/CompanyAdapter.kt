package com.jim.multipos.ui.adminmainpage.fragments.company

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminItemCompanyBinding
import com.jim.multipos.ui.base.BaseAdapter
import com.jim.multipos.ui.base.BaseClickListener
import com.jim.multipos.ui.base.BaseViewHolder


class CompanyAdapter(var context: Context):
        BaseAdapter<String, BaseClickListener<String>, CompanyAdapter.CompanyViewHolder>(){

    var lastPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
        return CompanyViewHolder(AdminItemCompanyBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    inner class CompanyViewHolder(binding: AdminItemCompanyBinding) :
            BaseViewHolder<String, BaseClickListener<String>>(binding.root){

        var mViewBinding: AdminItemCompanyBinding?=null
        var mViewModel: CompanyItemViewModel?=null

        init {
            mViewBinding = binding
        }

        override fun onBind(item: String, listener: BaseClickListener<String>) {

            mViewModel = CompanyItemViewModel(item)
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