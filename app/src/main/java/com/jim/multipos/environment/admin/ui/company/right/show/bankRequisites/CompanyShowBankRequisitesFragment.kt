package com.jim.multipos.environment.admin.ui.company.right.show.bankRequisites

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import com.google.gson.Gson
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyBankRequisitesViewLayoutBinding
import com.jim.multipos.environment.admin.model.CompanyRequisiteInformation
import com.jim.multipos.environment.admin.model.Requisite
import kotlinx.android.synthetic.main.company_bank_requisites_list_layout.*
import java.util.*
import javax.inject.Inject

class CompanyShowBankRequisitesFragment: BaseFragment<CompanyBankRequisitesViewLayoutBinding, CompanyShowRequisitesViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null){
            val json = arguments?.getString("bankRequisites", "")
            val contacts = Gson().fromJson(json, Array<Requisite>::class.java)
            mViewModel?.requisiteInformation = CompanyRequisiteInformation(contacts.toCollection(ArrayList()))
        }

        mViewModel?.onViewCreated()

        LayoutInflater
                .from(context)
                .inflate(R.layout.company_bank_requisites_view_layout, flBankRequisitesInfo, true)

        rvCompanyShowBankRequisitesRV.setBackgroundColor(ContextCompat.getColor(context!!, R.color.colorLightGray))
    }



    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_bank_requisites_list_layout
    override fun getViewModel(): CompanyShowRequisitesViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyShowRequisitesViewModel::class.java]
        return mViewModel as CompanyShowRequisitesViewModel
    }
}