package com.jim.multipos.environment.admin.ui.company.right.addEdit.address

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.RIGHT_FRAGMENT_TAG
import com.jim.multipos.databinding.CompanyAddressLayoutBinding
import com.jim.multipos.environment.admin.model.AddressInformation
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.company_address_layout.*
import javax.inject.Inject

class AddressCompanyFragment: BaseFragment<CompanyAddressLayoutBinding, AddressCompanyViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null && arguments!!["addressInformation"] != null) {
            mViewModel?.addressInformation = arguments!!["addressInformation"] as? AddressInformation
        }
        initUI()
        mViewModel?.onViewCreated()
        initObservers()
    }

    private fun initUI() {
        mpCountrySpinner.setItemSelected {
            if (it == 0) {
                mpCitySpinner.setItems(arrayOf(getString(R.string.select_your_country_first)))
            } else {
                mViewModel?.selectCountry(it - 1)
            }
        }

        mpCitySpinner.setItemSelected {
            if (it != 0) {
                mViewModel?.selectCity(it - 1)
            }
        }

        mpRadioButton.setList(
                listOf(
                        getString(R.string.office),
                        getString(R.string.home)
                )
        )

    }

    private fun initObservers() {

        mViewModel?.fillContentAction?.observe(this, Observer {

            val addressInformation = mViewModel?.addressInformation

            mpRadioButton.position
            etStreet1.setText(addressInformation?.streetAddress1)
            etStreet2.setText(addressInformation?.streetAddress2)

            val country = addressInformation?.country
            if (!country.isNullOrEmpty()) {
                mpCountrySpinner.selectItem(country)
            }

            etState.setText(addressInformation?.state)
            etPostCode.setText(addressInformation?.postCode)
            etCompanyAddressDescription.setText(addressInformation?.description)
        })

        mViewModel?.countryListLiveData?.observe(this, Observer {
            val countryList = it?.map { country ->
                country.name
            }
            val mutableCountryList = countryList?.toMutableList()
            mutableCountryList?.add(0, getString(R.string.select_your_country))
            mpCountrySpinner.setItems(mutableCountryList?.toTypedArray())
            mViewModel?.initAddressInformation()
        })

        mViewModel?.cityListLiveData?.observe(this, Observer {
            val result = it?.toMutableList()
            result?.add(0, getString(R.string.select_city))
            mpCitySpinner.setItems(result?.toTypedArray())

            val city = mViewModel?.addressInformation?.city
            if (!city.isNullOrEmpty()) {
                mpCitySpinner.selectItem(city)
            }

        })

        mViewModel?.isLoading?.observe(this, Observer {
            pbCompanyAddressAddEdit.visibility = if (it == true) { View.VISIBLE } else { View.GONE }
            llCompanyAddressContent.visibility = if (it == true) { View.GONE } else { View.VISIBLE }
        })

        mViewModel?.setAddressInformationAction?.observe(this, Observer {
            mViewModel?.addressInformation?.streetAddress1 = etStreet1.text.toString()
            mViewModel?.addressInformation?.streetAddress2 = etStreet2.text.toString()
            mViewModel?.addressInformation?.state = etState.text.toString()
            mViewModel?.addressInformation?.postCode = etPostCode.text.toString()
            mViewModel?.addressInformation?.description = etCompanyAddressDescription.text.toString()
            sendNotification(RIGHT_FRAGMENT_TAG, FragmentCommunicationOperations.DELIVER_DATA.operation, mViewModel?.addressInformation)
        })

    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_address_layout
    override fun getViewModel(): AddressCompanyViewModel {
        mViewModel = ViewModelProviders.of(this, factory).get(AddressCompanyViewModel::class.java)
        return mViewModel as AddressCompanyViewModel
    }

    fun deliverDataToMainClass() {
        mViewModel?.deliverDataToMainClass()
    }

}


