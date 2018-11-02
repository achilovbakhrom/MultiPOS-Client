package com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson

import android.arch.lifecycle.ViewModelProviders
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.databinding.CompanyContactPersonFragmentBinding
import javax.inject.Inject

class ContactPersonFragment: BaseFragment<CompanyContactPersonFragmentBinding, ContactPersonViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_contact_person_fragment
    override fun getViewModel(): ContactPersonViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(ContactPersonViewModel::class.java)
        return mViewModel as ContactPersonViewModel
    }

    fun deliverDataToMainClass() {}
}