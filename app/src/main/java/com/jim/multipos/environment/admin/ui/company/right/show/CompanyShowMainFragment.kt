package com.jim.multipos.environment.admin.ui.company.right.show

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.jim.multipos.R
import com.jim.multipos.core.Notifiable
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.core.fragments.BaseFragment
import com.jim.multipos.customView.MPTabBar
import com.jim.multipos.databinding.CompanyShowMainFragmentBinding
import com.jim.multipos.environment.admin.model.CompanyDTO
import com.jim.multipos.environment.admin.ui.company.right.show.bankRequisites.CompanyShowBankRequisitesFragment
import com.jim.multipos.environment.admin.ui.company.right.show.company.CompanyShowAboutFragment
import com.jim.multipos.environment.admin.ui.company.right.show.contactPerson.CompanyShowContactPersonFragment
import com.jim.multipos.utils.FragmentCommunicationOperations
import kotlinx.android.synthetic.main.company_show_main_fragment.*
import javax.inject.Inject

class CompanyShowMainFragment: BaseFragment<CompanyShowMainFragmentBinding, CompanyShowMainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    var companyDTO: CompanyDTO? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState?.getSerializable("model") != null) {
            companyDTO = savedInstanceState.getSerializable("model") as? CompanyDTO
        }
        if (arguments?.getSerializable("model") != null) {
            companyDTO = arguments?.getSerializable("model") as? CompanyDTO
        }
        mpCompanyShowMainTabBar.fragmentProtocol = object : MPTabBar.FragmentForTab {
            override fun getFragment(forPosition: Int): Fragment {
                return when(forPosition) {
                    0 -> CompanyShowAboutFragment() // Company About
                    1 -> CompanyShowContactPersonFragment() // Contact Person
                    2 -> CompanyShowBankRequisitesFragment() // Bank Requisites
                    else -> Fragment() // otherwise
                }
            }
        }
        initObservers()
    }

    private fun initObservers() {

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