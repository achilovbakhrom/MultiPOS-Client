package com.jim.multipos.environment.admin.ui.company.right.show.bankRequisites

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyBankRequisitesViewLayoutBinding
import javax.inject.Inject

class CompanyShowBankRequisitesFragment: BaseFragment<CompanyBankRequisitesViewLayoutBinding, CompanyShowRequisitesViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_bank_requisites_view_layout
    override fun getViewModel(): CompanyShowRequisitesViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyShowRequisitesViewModel::class.java]
        return mViewModel as CompanyShowRequisitesViewModel
    }
}