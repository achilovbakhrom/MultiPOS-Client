package com.jim.multipos.environment.admin.ui.company.right.show.contactPerson

import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyPersonViewListLayoutBinding
import kotlinx.android.synthetic.main.company_person_view_list_layout.*
import javax.inject.Inject

class CompanyShowContactPersonFragment: BaseFragment<CompanyPersonViewListLayoutBinding, CompanyShowContactPersonViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mpCompanyShowContactPersonRV.setRVBackgroundColor(ContextCompat.getColor(context!!, R.color.colorLightGray))
    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_person_view_list_layout
    override fun getViewModel(): CompanyShowContactPersonViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyShowContactPersonViewModel::class.java]
        return mViewModel as CompanyShowContactPersonViewModel
    }

}