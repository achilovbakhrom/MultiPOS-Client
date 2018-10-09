package com.jim.multipos.environment.admin.ui.company.left

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyLeftFragmentBinding
import javax.inject.Inject

class CompanyLeftFragment: BaseFragment<CompanyLeftFragmentBinding, CompanyLeftViewModel>() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private var mViewModel: CompanyLeftViewModel? = null
    private var mViewDataBinding: CompanyLeftFragmentBinding? = null
//    private var adapter: CompanyAdapter?=null

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.company_left_fragment
    }

    override fun getViewModel(): CompanyLeftViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CompanyLeftViewModel::class.java)
        return mViewModel as CompanyLeftViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewDataBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
//        adapter = CompanyAdapter(context!!)
//        adapter?.listener = object : BaseActions<String>{
//            override fun onItemClick(item: String) {
//
//            }
//        }
//        rvCompanies.itemAnimator.changeDuration = 0
//        rvCompanies.layoutManager = GridLayoutManager(context, 2)
//        rvCompanies.adapter = adapter
//        mViewModel?.getLiveData()?.observe(this, Observer {
//            adapter?.setItems(it!!)
//        })

    }

    fun update(){
        mViewModel?.fetch()
    }

}