package com.jim.multipos.environment.admin.ui


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.core.BaseActivity
import com.jim.multipos.customView.MpToolbar
import com.jim.multipos.databinding.AdminMainpageLayoutBinding
import com.jim.multipos.environment.admin.ui.company.main.CompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.addEdit.about.AboutCompanyFragment
import com.jim.multipos.environment.admin.ui.entities.EntitiesFragment
import com.jim.multipos.environment.admin.ui.establishment.EstablishmentFragment
import com.theartofdev.edmodo.cropper.CropImage
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.admin_mainpage_layout.*
import javax.inject.Inject


class MainPageActivity: BaseActivity<AdminMainpageLayoutBinding, MainPageViewModel>(), HasSupportFragmentInjector {

    companion object {
        const val COMPANY_FRAGMENT = "COMPANY_FRAGMENT"
        const val ESTABLISHMENT_FRAGMENT = "ESTABLISHMENT_FRAGMENT"
        const val ENTITIES_FRAGMENT = "ENTITIES_FRAGMENT"
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

            override fun onDashboard() {}

            override fun onEstablishment() {
                openFragment(EstablishmentFragment(), ESTABLISHMENT_FRAGMENT)
            }

            override fun onEntities() {
                openFragment(EntitiesFragment(), ENTITIES_FRAGMENT)
            }

            override fun onManagements() {}

            override fun onInventory() {}

            override fun onCRM() {}

            override fun onHRM() {}

            override fun onReports() {}

        })

        openFragment(CompanyFragment(), COMPANY_FRAGMENT)
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        addFragmentWithoutBackStack(fragment, R.id.container, tag)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val fragments = supportFragmentManager.fragments
            for (fragment in fragments) {
                if (fragment is AboutCompanyFragment) {
                    fragment.onActivityResult(requestCode, resultCode, data)
                    break
                }
            }
        }

    }
}