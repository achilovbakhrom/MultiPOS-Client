package com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.RIGHT_FRAGMENT_TAG
import com.jim.multipos.databinding.CompanyContactPersonAddEditLayoutBinding
import com.jim.multipos.environment.admin.model.CompanyContactPerson
import com.jim.multipos.environment.admin.model.ContactOrBankAddEdit
import com.jim.multipos.environment.admin.ui.company.right.show.CompanyShowMainFragment
import com.jim.multipos.utils.CompanyEditCreate
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.company_contact_person_add_edit_layout.*
import javax.inject.Inject

class ContactPersonAddEditFragment: BaseFragment<CompanyContactPersonAddEditLayoutBinding, ContactPersonAddEditViewModel>() {


    @Inject
    lateinit var factory: ViewModelProviderFactory
    var position = -1


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            mViewModel?.contactInformation = arguments?.getSerializable("contact_person") as CompanyContactPerson
            position = arguments?.getInt("pos", 0)!!
        }

        mViewModel?.onViewCreated()

        initUI()
        initObservables()
    }

    private fun initUI() {
        btnContactPersonCancel.setOnClickListener {
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation,
                    ContactOrBankAddEdit(CompanyEditCreate.CLOSE_CONTACT.operation,""))
        }

        btnContactPersonSave.setOnClickListener {
            mViewModel?.deliverDataToMainClass()
        }
    }

    private fun initObservables(){
        mViewModel?.setContactPerson?.observe(this, Observer {
            if(mViewModel?.contactInformation == null) {
                mViewModel?.contactInformation = CompanyContactPerson(
                        etPersonFirstName.text.toString(),
                        etContactPersonLastName.text.toString(),
                        "","", 1, 1,
                        etContactPersonNationality.text.toString(),
                        "", null,
                        etContactPersonOffice.text.toString(),
                        "", "",
                        etContactPersonEmail.text.toString(),
                        "", ""
                )
                sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation,
                        ContactOrBankAddEdit(CompanyEditCreate.SAVE_CONTACT.operation, mViewModel?.contactInformation!!))
            }else{
                mViewModel?.contactInformation?.firstName = etPersonFirstName.text.toString()
                sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation,
                        ContactOrBankAddEdit(CompanyEditCreate.SET_CONTACT.operation, mViewModel?.contactInformation!!, position))
            }

        })

        mViewModel?.fillContactPerson?.observe(this, Observer {
            etPersonFirstName.setText(mViewModel?.contactInformation?.firstName)
        })

    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_contact_person_add_edit_layout
    override fun getViewModel(): ContactPersonAddEditViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[ContactPersonAddEditViewModel::class.java]
        return mViewModel as ContactPersonAddEditViewModel
    }


}