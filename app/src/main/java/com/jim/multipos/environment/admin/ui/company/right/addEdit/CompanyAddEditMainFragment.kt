package com.jim.multipos.environment.admin.ui.company.right.addEdit

import android.arch.lifecycle.ViewModelProviders
import com.android.databinding.library.baseAdapters.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyAddEditMainFragmentBinding
import javax.inject.Inject

class CompanyAddEditMainFragment: BaseFragment<CompanyAddEditMainFragmentBinding, CompanyAddEditMainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory



    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_add_edit_main_fragment
    override fun getViewModel(): CompanyAddEditMainViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyAddEditMainViewModel::class.java]
        return mViewModel as CompanyAddEditMainViewModel
    }
}