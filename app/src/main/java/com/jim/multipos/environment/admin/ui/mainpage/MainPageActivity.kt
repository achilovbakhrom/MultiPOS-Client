package com.jim.multipos.environment.admin.ui.mainpage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView
import com.jim.multipos.BR
import com.jim.multipos.R
import com.jim.multipos.customView.MpToolbar
import com.jim.multipos.customView.adapter.SpinnerAdapter
import com.jim.multipos.databinding.AdminMainpageLayoutBinding
import com.jim.multipos.core.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.admin_mainpage_layout.*
import javax.inject.Inject
import android.widget.LinearLayout
import android.graphics.drawable.GradientDrawable
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.CompanyFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.DashboardEstablishmentFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.DashboardOrdersFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.DashboardPosFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.EstablishmentFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.productclass.ProductClassFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.products.ProductFragment



class MainPageActivity: BaseActivity<AdminMainpageLayoutBinding, MainPageViewModel>(), HasSupportFragmentInjector {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var adminMainPageViewModel: MainPageViewModel

    private var adminMainPageLayoutBinding: AdminMainpageLayoutBinding?=null

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

    private fun setUp(){
        toolbar.setItemSelected(object : MpToolbar.OnToolbarItemSelected {
            override fun onCompany() {
                openFragment(CompanyFragment())
            }

            override fun onDashboard() {
                openDashboard(DashboardPosFragment(), DashboardOrdersFragment(), DashboardEstablishmentFragment())
            }

            override fun onEstablishment() {
                openFragment(EstablishmentFragment())
            }

            override fun onEntities() {
                openEntity(ProductFragment())
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

        openFragment(CompanyFragment())
    }

    private fun openFragment(fragment: Fragment){
        dashboardContainer.visibility = GONE
        container.visibility = VISIBLE
        addFragmentWithoutBackStack(fragment, R.id.container)
    }

    private fun openEntity(fragment: Fragment){
        dashboardContainer.visibility = GONE
        container.visibility = GONE
        layoutEntities.visibility = VISIBLE
        addFragmentWithoutBackStack(fragment, R.id.entityContainer)
        setUpEntityTabs()
    }

    private fun openDashboard(leftFragment: Fragment, rightFragment: Fragment, topFragment: Fragment){
        dashboardContainer.visibility = VISIBLE
        container.visibility = GONE
        addFragmentWithoutBackStack(topFragment, R.id.topContainer)
        addFragmentWithoutBackStack(leftFragment, R.id.dashboardLeft)
        addFragmentWithoutBackStack(rightFragment, R.id.dashboardRight)
        setUpSpinner()
    }

    private fun setUpSpinner(){
        spinnerUnit.adapter = SpinnerAdapter(this, arrayOf("UZS", "USD", "RUB"))
        spinnerUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerUnit.setSelection(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        spinnerEstablishment.adapter = SpinnerAdapter(this, arrayOf("Establishment 1", "Establishment 2", "Establishment 3"))
        spinnerEstablishment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerEstablishment.setSelection(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun setUpEntityTabs(){
        entityTabs.removeAllTabs()
        entityTabs.addTab(entityTabs.newTab().setText(getString(R.string.products)))
        val view = entityTabs.getChildAt(0)
        (view as LinearLayout).showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        val drawable = GradientDrawable()
        drawable.setColor(ContextCompat.getColor(this, R.color.colorGray))
        drawable.setSize(1, 1)
        view.dividerDrawable = drawable

        entityTabs.addTab(entityTabs.newTab().setText(getString(R.string.product_class)))
        entityTabs.addTab(entityTabs.newTab().setText(getString(R.string.discount)))
        entityTabs.addTab(entityTabs.newTab().setText(getString(R.string.service_fee)))
        entityTabs.addTab(entityTabs.newTab().setText(getString(R.string.unit)))
        entityTabs.addTab(entityTabs.newTab().setText(getString(R.string.product_category)))
        entityTabs.addTab(entityTabs.newTab().setText(getString(R.string.import_)))
        entityTabs.addTab(entityTabs.newTab().setText(getString(R.string.export)))
        entityTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.text){
                    getString(R.string.products)->addFragmentWithoutBackStack(ProductFragment(), R.id.entityContainer)
                    getString(R.string.discount)->addFragmentWithoutBackStack(EstablishmentFragment(), R.id.entityContainer)
                    getString(R.string.product_class)->addFragmentWithoutBackStack(ProductClassFragment(), R.id.entityContainer)
                }
            }

        })
    }

}