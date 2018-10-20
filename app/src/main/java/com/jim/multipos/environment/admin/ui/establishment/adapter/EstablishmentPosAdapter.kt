package com.jim.multipos.environment.admin.ui.establishment.adapter

//class EstablishmentPosAdapter: BaseAdapter<String, BaseActions<String>, EstablishmentPosAdapter.ViewHolder>() {
//
//    var lastPos = -1
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return ViewHolder(AdminItemEstablishmentPosBinding.inflate(LayoutInflater.from(parent.context),parent, false))
//    }
//
//    inner class ViewHolder(binding: AdminItemEstablishmentPosBinding): BaseViewHolder<String, BaseActions<String>>(binding.root) {
//
//        var mViewBinding: AdminItemEstablishmentPosBinding? = null
//        var mViewModel: EstablishmentPosItemViewModel? = null
//
//        init {
//            mViewBinding = binding
//        }
//
//        override fun onBind(item: String, listener: BaseActions<String>) {
//            mViewModel = EstablishmentPosItemViewModel(item)
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
//
//    }
//}