package com.jim.multipos.environment.admin.ui.dashboard.adapter

//class DashboardPosAdapter(context: Context):
//        BaseAdapter<String, BaseActions<String>, DashboardPosAdapter.PosViewHolder>(){
//
//    var lastPos = -1
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosViewHolder {
//        return PosViewHolder(AdminItemDashboardPosBinding.inflate(LayoutInflater.from(parent.context),parent, false))
//    }
//
//    inner class PosViewHolder(binding: AdminItemDashboardPosBinding):
//            BaseViewHolder<String, BaseActions<String>>(binding.root){
//
//        var mViewBinding: AdminItemDashboardPosBinding? = null
//        var mViewModel: DashboardItemViewModel? = null
//
//        init {
//            mViewBinding = binding
//        }
//
//        override fun onBind(item: String, listener: BaseActions<String>) {
//            mViewModel = DashboardItemViewModel(item)
//
//            mViewBinding?.root?.setOnClickListener {
//                listener.onItemClick(item)
//                if(lastPos!=-1) notifyItemChanged(lastPos)
//                lastPos = adapterPosition
//                notifyItemChanged(lastPos)
//            }
//            if (lastPos == adapterPosition)
//                mViewModel?.isClicked?.set(true)
//            else
//                mViewModel?.isClicked?.set(false)
//            mViewBinding?.viewModel= mViewModel
//            mViewBinding?.executePendingBindings()
//        }
//
//    }
//}