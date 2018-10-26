package com.jim.multipos.environment.admin.ui.company.left

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.SingleListFragment
import com.jim.multipos.databinding.CompanyLeftFragmentBinding
import javax.inject.Inject

class CompanyLeftFragment: BaseFragment<CompanyLeftFragmentBinding, CompanyLeftViewModel>() {

    override fun getLayoutId(): Int = R.layout.company_left_fragment

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getViewModel(): CompanyLeftViewModel {
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CompanyLeftViewModel::class.java)
        return mViewModel as CompanyLeftViewModel
    }

}