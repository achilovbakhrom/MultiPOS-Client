package com.jim.multipos.environment.admin.ui.mainpage

import android.os.Bundle
import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseActivity
import com.jim.multipos.customView.MpToolbar
import com.jim.multipos.databinding.AdminMainpageLayoutBinding
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.main.CompanyFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.EstablishmentFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.admin_mainpage_layout.*
import javax.inject.Inject


class MainPageActivity: BaseActivity<AdminMainpageLayoutBinding, MainPageViewModel>(), HasSupportFragmentInjector {

    companion object {
        val COMPANY_FRAGMENT = "COMPANY_FRAGMENT"
        val ESTABLISHMENT_FRAGMENT = "ESTABLISHMENT_FRAGMENT"
    }


    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var adminMainPageViewModel: MainPageViewModel

    private var adminMainPageLayoutBinding: AdminMainpageLayoutBinding? = null

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentDispatchingAndroidInjector
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun getLayoutId(): Int {
        return R.layout.admin_mainpage_layout
    }

    override fun getViewModel(): MainPageViewModel {
        return adminMainPageViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adminMainPageLayoutBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
        toolbar.setItemSelected(object : MpToolbar.OnToolbarItemSelected {
            override fun onCompany() {
                openFragment(CompanyFragment(), COMPANY_FRAGMENT)
            }

            override fun onDashboard() {
            }

            override fun onEstablishment() {
                openFragment(EstablishmentFragment(), ESTABLISHMENT_FRAGMENT)
            }

            override fun onEntities() {

            }

            override fun onManagements() {

            }

            override fun onInventory() {

            }

            override fun onCRM() {

            }

            override fun onHRM() {

            }

            override fun onReports() {

            }

        })

        openFragment(CompanyFragment(), COMPANY_FRAGMENT)
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        addFragmentWithoutBackStack(fragment, R.id.container, tag)
    }
}