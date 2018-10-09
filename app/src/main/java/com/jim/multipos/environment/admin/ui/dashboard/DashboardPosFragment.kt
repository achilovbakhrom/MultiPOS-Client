package com.jim.multipos.environment.admin.ui.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout.VERTICAL
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.databinding.AdminDashboardPosLayoutBinding
import com.jim.multipos.core.BaseActions
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.environment.admin.ui.dashboard.viewmodel.DashboardViewModel
import kotlinx.android.synthetic.main.admin_dashboard_pos_layout.*
import javax.inject.Inject

class DashboardPosFragment: BaseFragment<AdminDashboardPosLayoutBinding, DashboardViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory
    private var mViewModel: DashboardViewModel? = null
    private var mViewDataBinding: AdminDashboardPosLayoutBinding? = null
//    private var adapter: DashboardPosAdapter? = null


    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_dashboard_pos_layout
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
//
//        adapter = DashboardPosAdapter(context!!)
//        adapter?.listener = object : BaseActions<String> {
//            override fun onItemClick(item: String) {
//                mViewModel?.fetchOrders(item)
//            }
//        }
//        rvPos.itemAnimator.changeDuration = 0
//        rvPos.layoutManager = LinearLayoutManager(context)
//        rvPos.addItemDecoration(DividerItemDecoration(context, VERTICAL))
//        rvPos.adapter = adapter
//        mViewModel?.getPosData()?.observe(this, Observer { adapter?.setItems(it!!) })

    }
}