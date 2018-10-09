package com.jim.multipos.environment.admin.ui.dashboard.adapter


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