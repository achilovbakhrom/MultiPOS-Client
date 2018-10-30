package com.jim.multipos.environment.admin.ui.company.right.addEdit.address

import android.arch.lifecycle.ViewModelProviders
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyAddressLayoutBinding
import javax.inject.Inject

class AddressCompanyFragment: BaseFragment<CompanyAddressLayoutBinding, AddressCompanyViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory



    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_address_layout
    override fun getViewModel(): AddressCompanyViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(AddressCompanyViewModel::class.java)
        return mViewModel as AddressCompanyViewModel
    }

}
