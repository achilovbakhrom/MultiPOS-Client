package com.jim.multipos.environment.admin.ui.company

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jim.multipos.databinding.AdminItemCompanyBinding
import com.jim.multipos.core.adapter.BaseAdapter
import com.jim.multipos.core.BaseActions
import com.jim.multipos.core.adapter.BaseViewHolder


//class CompanyAdapter(var context: Context):
//        BaseAdapter<String, CompanyAdapter.CompanyViewHolder>(){

//    var lastPos = -1
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyViewHolder {
//        return CompanyViewHolder(AdminItemCompanyBinding.inflate(LayoutInflater.from(parent.context),parent, false))
//    }
//
//    inner class CompanyViewHolder(binding: AdminItemCompanyBinding) :
//            BaseViewHolder<String, BaseActions<String>>(binding.root){
//
//        var mViewBinding: AdminItemCompanyBinding? = null
//        var mViewModel: CompanyItemViewModel? = null
//
//        init {
//            mViewBinding = binding
//        }
//
//        override fun onBind(item: String, listener: BaseActions<String>) {
//
//            mViewModel = CompanyItemViewModel(item)
//            mViewBinding?.root?.setOnClickListener {
//                listener.onItemClick(item)
//                if(lastPos!=-1) notifyItemChanged(lastPos)
//                lastPos = adapterPosition
//                notifyItemChanged(lastPos)
//            }
//
//            if (lastPos == adapterPosition)
//                mViewModel?.isClicked?.set(true)
//            else
//                mViewModel?.isClicked?.set(false)
//
//            mViewBinding?.viewModel = mViewModel!!
//            mViewBinding?.executePendingBindings()
//        }
//    }






//}