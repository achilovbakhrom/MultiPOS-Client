package com.jim.multipos.environment.admin.ui.company.right.addEdit.bankRequisites

import android.arch.lifecycle.ViewModelProviders
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyBankRequisitesListFragmentBinding
import javax.inject.Inject

class BankRequisitesFragment: BaseFragment<CompanyBankRequisitesListFragmentBinding, BankRequisitesViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_bank_requisites_list_fragment
    override fun getViewModel(): BankRequisitesViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(BankRequisitesViewModel::class.java)
        return mViewModel as BankRequisitesViewModel
    }
}