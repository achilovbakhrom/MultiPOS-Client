package com.jim.multipos.environment.admin.ui.company.right.show.company

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.RIGHT_FRAGMENT_TAG
import com.jim.multipos.databinding.CompanyDetailsViewLayoutBinding
import com.jim.multipos.environment.admin.model.Company
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.company_details_view_layout.*
import javax.inject.Inject

class CompanyShowAboutFragment: BaseFragment<CompanyDetailsViewLayoutBinding, CompanyShowAboutViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null&&arguments?.getSerializable("model") != null){
            mViewModel?.company = arguments?.getSerializable("model") as Company
            initUI()
        }


        btnCompanyAboutEdit.setOnClickListener {
            mViewModel?.company?.editMode = true
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation, mViewModel?.company)
        }
    }

    private fun initUI(){
        tvCompanyName.text = mViewModel?.company?.name
        tvCompanyOccupation.text = mViewModel?.company?.occupation
        if(mViewModel?.company?.contactData!=null && mViewModel?.company?.contactData?.size!!>0){
            for(contact in mViewModel?.company?.contactData!!){
                if(contact.type == 0)
                    tvCompanyPhone.text = tvCompanyPhone.text.toString().plus("\n").plus(contact.data)
                else tvCompanyEmail.text = tvCompanyEmail.text.toString().plus("\n").plus(contact.data)

            }

        }else{
            tvCompanyEmail.text = ""
            tvCompanyPhone.text = ""
        }
        tvCompanyDescription.text = mViewModel?.company?.description
        tvCompanyAddress.text = mViewModel?.company?.addressInformation?.type
        tvCompanyStrAddress1.text = mViewModel?.company?.addressInformation?.streetAddress1
        tvCompanyStrAddress2.text = mViewModel?.company?.addressInformation?.streetAddress2
        tvCompanyCountry.text = mViewModel?.company?.addressInformation?.country
        tvCompanyCity.text = mViewModel?.company?.addressInformation?.city
        tvCompanyState.text = mViewModel?.company?.addressInformation?.state
        tvCompanyPostalCode.text = mViewModel?.company?.addressInformation?.postCode
        tvCompanyAddressDescription.text = mViewModel?.company?.addressInformation?.description
    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_details_view_layout
    override fun getViewModel(): CompanyShowAboutViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyShowAboutViewModel::class.java]
        return mViewModel as CompanyShowAboutViewModel
    }

}