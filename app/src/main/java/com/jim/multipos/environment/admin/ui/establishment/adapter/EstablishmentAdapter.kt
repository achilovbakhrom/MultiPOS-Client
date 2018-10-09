//package com.jim.multipos.environment.admin.ui.establishment.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import com.jim.multipos.databinding.AdminItemEstablishmentBinding
//import com.jim.multipos.environment.admin.ui.establishment.viewmodel.EstablishmentItemViewModel
//import com.jim.multipos.core.adapter.BaseAdapter
//import com.jim.multipos.core.BaseActions
//import com.jim.multipos.core.adapter.BaseViewHolder
//
//class EstablishmentAdapter: BaseAdapter<String, BaseActions<String>, EstablishmentAdapter.EstablishmentViewHolder>() {
//
//    var lastPos = -1
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstablishmentViewHolder {
//        return EstablishmentViewHolder(AdminItemEstablishmentBinding.inflate(LayoutInflater.from(parent.context),parent, false))
//    }
//
//    inner class EstablishmentViewHolder(binding:AdminItemEstablishmentBinding):
//            BaseViewHolder<String, BaseActions<String>>(binding.root) {
//
//        var mViewBinding: AdminItemEstablishmentBinding? = null
//        var mViewModel: EstablishmentItemViewModel? = null
//
//        init {
//            mViewBinding = binding
//        }
//
//        override fun onBind(item: String, listener: BaseActions<String>) {
//            mViewModel = EstablishmentItemViewModel(item)
//            mViewBinding!!.root.setOnClickListener {
//                listener.onItemClick(item)
//                if(lastPos!=-1) notifyItemChanged(lastPos)
//                lastPos = adapterPosition
//                notifyItemChanged(lastPos)
//            }
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