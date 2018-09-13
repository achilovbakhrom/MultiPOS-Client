package com.jim.multipos.ui.adminmainpage.fragments.dashboard

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminDashboardEstablishmentLayoutBinding
import com.jim.multipos.ui.adminmainpage.fragments.dashboard.viewmodel.DashboardViewModel
import com.jim.multipos.ui.base.BaseFragment
import kotlinx.android.synthetic.main.admin_dashboard_establishment_layout.*
import javax.inject.Inject

class DashboardEstablishmentFragment: BaseFragment<AdminDashboardEstablishmentLayoutBinding, DashboardViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    private var mViewModel: DashboardViewModel?=null
    private var mViewDataBinding: AdminDashboardEstablishmentLayoutBinding?=null


    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_dashboard_establishment_layout
    }

    override fun getViewModel(): DashboardViewModel {
        mViewModel = ViewModelProviders.of(activity!!, mViewModelFactory).get(DashboardViewModel::class.java)
        return mViewModel as DashboardViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding = getViewDataBinding()

        revenue.setPercentAndText(50f, "Revenue", "100 000 000 000")
        profit.setPercentAndText(70f, "Profit", "30 000 000")
        payout.setPercentAndText(40f, "Pay Out", "30000")
        dept.setPercentAndText(80f, "Dept", "30")

    }

}