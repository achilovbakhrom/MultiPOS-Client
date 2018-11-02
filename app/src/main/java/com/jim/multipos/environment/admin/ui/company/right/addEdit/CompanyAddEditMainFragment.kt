package com.jim.multipos.environment.admin.ui.company.right.addEdit

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.jim.multipos.R
import com.jim.multipos.core.Notifiable
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.LEFT_FRAGMENT_TAG
import com.jim.multipos.customView.MPTabBar
import com.jim.multipos.databinding.CompanyAddEditMainFragmentBinding
import com.jim.multipos.environment.admin.model.AboutInformation
import com.jim.multipos.environment.admin.model.AddressInformation
import com.jim.multipos.environment.admin.ui.company.right.addEdit.about.AboutCompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.addEdit.address.AddressCompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.addEdit.bankRequisites.BankRequisitesFragment
import com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson.ContactPersonFragment
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.company_add_edit_main_fragment.*
import javax.inject.Inject

class CompanyAddEditMainFragment: BaseFragment<CompanyAddEditMainFragmentBinding, CompanyAddEditMainViewModel>(), Notifiable {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private var aboutFragment: AboutCompanyFragment? = null
    private var addressCompanyFragment: AddressCompanyFragment? = null
    private var contactPersonFragment: ContactPersonFragment? = null
    private var bankRequisitesFragment: BankRequisitesFragment? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel?.onViewCreated()
        mpCompanyAddEditTabBar.fragmentProtocol = object : MPTabBar.FragmentForTab {
            override fun getFragment(forPosition: Int): Fragment {
                return when(forPosition) {
                    0 -> {
                        aboutFragment = AboutCompanyFragment()
                        val company = mViewModel?.companyDTO?.company
                        if (company?.name?.isEmpty() == false ||
                                company?.occupation?.isEmpty() == false ||
                                company?.contactData?.isEmpty() == false ||
                                company?.description?.isEmpty() == false ||
                                !company?.localPhotoPath.isNullOrEmpty()) {
                            val bundle = Bundle()
                            val aboutInformation = AboutInformation()
                            aboutInformation.companyName = company?.name ?: ""
                            aboutInformation.companyOccupation = company?.occupation ?: ""
                            aboutInformation.contactData = company?.contactData
                            aboutInformation.imagePath = company?.localPhotoPath
                            aboutInformation.description = company?.description
                            bundle.putSerializable("aboutInformation", aboutInformation)
                            aboutFragment?.arguments = bundle
                        }
                        aboutFragment!!
                    }
                    1 -> {
                        addressCompanyFragment = AddressCompanyFragment()
                        val bundle = Bundle()
                        bundle.putSerializable("addressInformation", mViewModel?.companyDTO?.company?.addressInformation)
                        addressCompanyFragment?.arguments = bundle
                        addressCompanyFragment!!
                    }
                    2 -> {
                        contactPersonFragment = ContactPersonFragment()

                        contactPersonFragment!!
                    }
                    3 -> {
                        bankRequisitesFragment = BankRequisitesFragment()

                        bankRequisitesFragment!!
                    }
                    else -> Fragment()
                }
            }
        }
        mpCompanyAddEditTabBar.selectionListener = object : MPTabBar.FragmentSelectionListener {
            override fun fragmentSelected(position: Int, fragment: Fragment?) {
                aboutFragment?.deliverDataToMainClass()
                addressCompanyFragment?.deliverDataToMainClass()
                contactPersonFragment?.deliverDataToMainClass()
                bankRequisitesFragment?.deliverDataToMainClass()
            }
        }

        initObservers()
    }

    override fun notify(action: String?, data: Any?) {
        when(action) {
            FragmentCommunicationOperations.DELIVER_DATA.operation -> {
                when (data) {
                    is AboutInformation -> {
                         mViewModel?.setAboutInformation(data)
                    }
                    is AddressInformation -> {
                        mViewModel?.setAddressInformation(data)
                    }
                }
            }
        }
    }

    private fun initObservers() {
        mViewModel?.saveCompanyAction?.observe(this, Observer {
            sendNotification(LEFT_FRAGMENT_TAG, FragmentCommunicationOperations.ITEM_ADDED.operation, mViewModel?.companyDTO)
        })
    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_add_edit_main_fragment
    override fun getViewModel(): CompanyAddEditMainViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyAddEditMainViewModel::class.java]
        return mViewModel as CompanyAddEditMainViewModel
    }
}