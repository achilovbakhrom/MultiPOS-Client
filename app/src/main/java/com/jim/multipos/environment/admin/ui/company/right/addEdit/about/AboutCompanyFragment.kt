package com.jim.multipos.environment.admin.ui.company.right.addEdit.about

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.AboutCompanyFragmentBinding
import javax.inject.Inject

class AboutCompanyFragment: BaseFragment<AboutCompanyFragmentBinding, AboutCompanyViewModel>() {
    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.about_company_fragment
    override fun getViewModel(): AboutCompanyViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(AboutCompanyViewModel::class.java)
        return mViewModel as AboutCompanyViewModel
    }

}
