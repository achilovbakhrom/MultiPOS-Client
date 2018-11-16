package com.jim.multipos.environment.admin.ui.company.right.show

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.google.gson.Gson
import com.jim.multipos.R
import com.jim.multipos.core.Notifiable
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment
import com.jim.multipos.core.fragments.DoubleHorizontalFragment.Companion.LEFT_FRAGMENT_TAG
import com.jim.multipos.customView.MPTabBar
import com.jim.multipos.databinding.CompanyShowMainFragmentBinding
import com.jim.multipos.environment.admin.model.*
import com.jim.multipos.environment.admin.ui.company.right.addEdit.about.AboutCompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.addEdit.address.AddressCompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson.ContactPersonAddEditFragment
import com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson.ContactPersonFragment
import com.jim.multipos.environment.admin.ui.company.right.show.bankRequisites.CompanyShowBankRequisitesFragment
import com.jim.multipos.environment.admin.ui.company.right.show.company.CompanyShowAboutFragment
import com.jim.multipos.environment.admin.ui.company.right.show.contactPerson.CompanyShowContactPersonFragment
import com.jim.multipos.utils.CompanyEditCreate
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.company_add_edit_main_fragment.*
import kotlinx.android.synthetic.main.company_show_main_fragment.*
import javax.inject.Inject

class CompanyShowMainFragment: BaseFragment<CompanyShowMainFragmentBinding, CompanyShowMainViewModel>(), Notifiable {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    var companyDTO: CompanyDTO? = null
    var companyShowAboutFragment: CompanyShowAboutFragment?=null
    var aboutCompanyFragment: AboutCompanyFragment?=null
    var addressCompanyFragment: AddressCompanyFragment?= null
    var companyShowContactPersonFragment: CompanyShowContactPersonFragment?=null
    var editMode: Boolean = false
    var editContact: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState?.getSerializable("model") != null) {
            companyDTO = savedInstanceState.getSerializable("model") as? CompanyDTO
        }
        if (arguments?.getSerializable("model") != null) {
            companyDTO = arguments?.getSerializable("model") as? CompanyDTO
            mViewModel?.companyDTO = companyDTO!!
        }
        mpCompanyShowMainTabBar.fragmentProtocol = object : MPTabBar.FragmentForTab {
            override fun getFragment(forPosition: Int): Fragment {
                return when(forPosition) {
                    0 -> {
                        if(editMode){
                            aboutCompanyFragment = AboutCompanyFragment()
                            val bundle = Bundle()
                            val info = AboutInformation("", mViewModel?.companyDTO?.company?.name?:"",
                                    mViewModel?.companyDTO?.company?.occupation?:"",
                                    mViewModel?.companyDTO?.company?.contactData,
                                    mViewModel?.companyDTO?.company?.description, editMode = true)
                            bundle.putSerializable("aboutInformation", info)
                            aboutCompanyFragment?.arguments = bundle
                            aboutCompanyFragment!!
                        }else {
                            companyShowAboutFragment = CompanyShowAboutFragment()
                            val bundle = Bundle()
                            bundle.putSerializable("model", mViewModel?.companyDTO?.company)
                            companyShowAboutFragment?.arguments = bundle
                            companyShowAboutFragment!!
                        }
                    }
                    1 -> {
                        if(editMode){
                            addressCompanyFragment = AddressCompanyFragment()
                            val bundle = Bundle()
                            mViewModel?.companyDTO?.company?.addressInformation?.editMode = true
                            bundle.putSerializable("addressInformation",mViewModel?.companyDTO?.company?.addressInformation)
                            addressCompanyFragment?.arguments = bundle
                            addressCompanyFragment!!
                        }else {
                            companyShowContactPersonFragment = CompanyShowContactPersonFragment()
                            val bundle = Bundle()
                            val json = Gson().toJson(mViewModel?.companyDTO?.companyContactPersons)
                            bundle.putString("contactPerson", json)
                            companyShowContactPersonFragment?.arguments = bundle
                            companyShowContactPersonFragment!!
                        }
                    }
                    2 -> {
                        mpCompanyShowMainTabBar.editMode = false
                        CompanyShowBankRequisitesFragment()
                    } // Bank Requisites
                    else -> Fragment() // otherwise
                }
            }
        }
        initObservers()
    }

    private fun initObservers() {

    }

    override fun notify(action: String?, data: Any?) {
        when(action) {
            FragmentCommunicationOperations.DELIVER_DATA.operation -> {
                when (data) {
                    is Company -> {
                        editMode = data.editMode
                        if(editMode){
                            mViewModel?.companyDTO?.company?.editMode = true
                            mpCompanyShowMainTabBar.setTabItems(mutableListOf(getString(R.string.about), getString(R.string.address)))
                        }
                        mpCompanyShowMainTabBar.editMode = editMode
                        mpCompanyShowMainTabBar.selectedPosition = 0
                    }
                    is AboutInformation -> {
                        mViewModel?.aboutInformation = data
                        mpCompanyShowMainTabBar.selectedPosition = 1
                    }
                    is AddressInformation -> {
                        mViewModel?.addressInformation = data
                        mViewModel?.setCompany()
                        editMode = false
                        mpCompanyShowMainTabBar.setTabItems(resources.getStringArray(R.array.company_show_items).toMutableList())
                        mpCompanyShowMainTabBar.selectedPosition = 0
                        mpCompanyShowMainTabBar.editMode = editMode
                        sendNotification(LEFT_FRAGMENT_TAG, FragmentCommunicationOperations.ITEM_EDITED.operation, mViewModel?.companyDTO)
                    }
                    is ContactOrBankAddEdit -> {
                        when(data.key){
                            CompanyEditCreate.ADD_CONTACT.operation -> {
                                mpCompanyShowMainTabBar.visibility = GONE
                                flNewItemContainer.visibility = VISIBLE
                                editContact = false
                                openFragment(ContactPersonAddEditFragment())
                            }
                            CompanyEditCreate.EDIT_CONTACT.operation ->{
                                mpCompanyShowMainTabBar.visibility = GONE
                                flNewItemContainer.visibility = VISIBLE
                                editContact = true
                                val bundle = Bundle()
                                bundle.putSerializable("contact_person", data.model as CompanyContactPerson)
                                if(data.position!=-1)
                                    bundle.putInt("pos", data.position)
                                val fragment = ContactPersonAddEditFragment()
                                fragment.arguments = bundle
                                openFragment(fragment)
                            }
                            CompanyEditCreate.SAVE_CONTACT.operation -> {
                                mpCompanyShowMainTabBar.visibility = VISIBLE
                                flNewItemContainer.visibility = GONE
                                mViewModel?.companyDTO?.companyContactPersons?.add(data.model as CompanyContactPerson)
                                if(mpCompanyShowMainTabBar.currentFragment is CompanyShowContactPersonFragment) {
                                    (mpCompanyShowMainTabBar.currentFragment as CompanyShowContactPersonFragment).addItem(data.model as CompanyContactPerson)
                                }
                                sendNotification(LEFT_FRAGMENT_TAG, FragmentCommunicationOperations.ITEM_EDITED.operation, mViewModel?.companyDTO)
                            }
                            CompanyEditCreate.SET_CONTACT.operation -> {
                                mpCompanyShowMainTabBar.visibility = VISIBLE
                                flNewItemContainer.visibility = GONE
                                mViewModel?.companyDTO?.companyContactPersons?.set(data.position ,data.model as CompanyContactPerson)
                                mViewModel?.companyDTO?.companyContactPersons?.set(data.position ,data.model as CompanyContactPerson)
                                if(mpCompanyShowMainTabBar.currentFragment is CompanyShowContactPersonFragment) {
                                    (mpCompanyShowMainTabBar.currentFragment as CompanyShowContactPersonFragment).setItem(data.model as CompanyContactPerson)
                                    (mpCompanyShowMainTabBar.currentFragment as CompanyShowContactPersonFragment).populateUI(data.model as CompanyContactPerson)
                                }
                                sendNotification(LEFT_FRAGMENT_TAG, FragmentCommunicationOperations.ITEM_EDITED.operation, mViewModel?.companyDTO)
                            }
                            CompanyEditCreate.DELETE_CONTACT.operation -> {
                                mViewModel?.companyDTO?.companyContactPersons?.removeAt(data.position)
                                if(mpCompanyShowMainTabBar.currentFragment is CompanyShowContactPersonFragment) {
                                    (mpCompanyShowMainTabBar.currentFragment as CompanyShowContactPersonFragment).deleteItem()
                                }
                            }
                            CompanyEditCreate.CLOSE_CONTACT.operation -> {
                                mpCompanyShowMainTabBar.visibility = VISIBLE
                                flNewItemContainer.visibility = GONE
                            }
                        }
                    }
                    else -> {
                        editMode = false
                        mpCompanyShowMainTabBar.setTabItems(resources.getStringArray(R.array.company_show_items).toMutableList())
                        mpCompanyShowMainTabBar.editMode = editMode
                        mpCompanyShowMainTabBar.selectedPosition = 0
                    }
                }
            }
        }
    }

    private fun openFragment(fragment: Fragment){
        val appCompatActivity = context as AppCompatActivity
        appCompatActivity
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.flNewItemContainer, fragment)
                .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("model", companyDTO)
    }

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.company_show_main_fragment
    override fun getViewModel(): CompanyShowMainViewModel {
        mViewModel = ViewModelProviders.of(this, factory)[CompanyShowMainViewModel::class.java]
        return mViewModel as CompanyShowMainViewModel
    }
}