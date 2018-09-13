package com.jim.multipos.ui.adminmainpage.fragments.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminDashboardOrdersLayoutBinding
import com.jim.multipos.ui.adminmainpage.fragments.dashboard.adapter.DashboardOrdersAdapter
import com.jim.multipos.ui.adminmainpage.fragments.dashboard.viewmodel.DashboardViewModel
import com.jim.multipos.ui.base.BaseClickListener
import com.jim.multipos.ui.base.BaseFragment
import kotlinx.android.synthetic.main.admin_dashboard_orders_layout.*
import javax.inject.Inject

class DashboardOrdersFragment: BaseFragment<AdminDashboardOrdersLayoutBinding, DashboardViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    private var mViewModel: DashboardViewModel?=null
    private var mViewDataBinding: AdminDashboardOrdersLayoutBinding?=null
    private var adapter: DashboardOrdersAdapter?=null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_dashboard_orders_layout
    }

    override fun getViewModel(): DashboardViewModel {
        mViewModel = ViewModelProviders.of(activity!!, mViewModelFactory).get(DashboardViewModel::class.java)
        return mViewModel as DashboardViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()
        setUpRV()
    }

    fun setUpRV(){
        adapter = DashboardOrdersAdapter()
        adapter!!.listener = object : BaseClickListener<String>{
            override fun onItemClick(item: String) {

            }
        }

        rvOrders.layoutManager = GridLayoutManager(context, 2)
        rvOrders.adapter = adapter
        mViewModel!!.getOrdersItem().observe(this, Observer { adapter!!.setItems(it!!) })
    }
}