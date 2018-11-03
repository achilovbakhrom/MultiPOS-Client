package com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyContactPersonAddEditLayoutBinding
import javax.inject.Inject

class ContactPersonAddEditFragment: BaseFragment<CompanyContactPersonAddEditLayoutBinding, ContactPersonAddEditViewModel>() {


    @Inject
    lateinit var factory: ViewModelProviderFactory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_contact_person_add_edit_layout
    override fun getViewModel(): ContactPersonAddEditViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[ContactPersonAddEditViewModel::class.java]
        return mViewModel as ContactPersonAddEditViewModel
    }


}