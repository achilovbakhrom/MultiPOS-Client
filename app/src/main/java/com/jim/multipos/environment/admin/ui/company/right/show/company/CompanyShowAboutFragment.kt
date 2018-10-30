package com.jim.multipos.environment.admin.ui.company.right.show.company

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyDetailsViewLayoutBinding
import javax.inject.Inject

class CompanyShowAboutFragment: BaseFragment<CompanyDetailsViewLayoutBinding, CompanyShowAboutViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_details_view_layout
    override fun getViewModel(): CompanyShowAboutViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyShowAboutViewModel::class.java]
        return mViewModel as CompanyShowAboutViewModel
    }

}