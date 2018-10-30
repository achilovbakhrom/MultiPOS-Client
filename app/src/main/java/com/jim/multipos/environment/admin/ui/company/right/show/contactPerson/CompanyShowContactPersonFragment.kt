package com.jim.multipos.environment.admin.ui.company.right.show.contactPerson

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyPersonViewListLayoutBinding
import javax.inject.Inject

class CompanyShowContactPersonFragment: BaseFragment<CompanyPersonViewListLayoutBinding, CompanyShowContactPersonViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_person_view_list_layout
    override fun getViewModel(): CompanyShowContactPersonViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyShowContactPersonViewModel::class.java]
        return mViewModel as CompanyShowContactPersonViewModel
    }

}