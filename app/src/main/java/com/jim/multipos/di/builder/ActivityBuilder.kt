package com.jim.multipos.di.builder

import com.jim.multipos.ui.adminmainpage.AdminMainPageActivity
import com.jim.multipos.ui.adminmainpage.AdminMainPageModule
import com.jim.multipos.ui.adminmainpage.fragments.company.CompanyFragmentProvider
import com.jim.multipos.ui.adminmainpage.fragments.dashboard.di.DashboardFragmentProvider
import com.jim.multipos.ui.adminmainpage.fragments.establishment.di.EstablishmentFragmentProvider
import com.jim.multipos.ui.adminmainpage.fragments.productclass.ProductClassFragmentProvider
import com.jim.multipos.ui.adminmainpage.fragments.products.ProductFragmentProvider
import com.jim.multipos.ui.adminsignin.AdminSignInActivity
import com.jim.multipos.ui.adminsignin.AdminSignInModule
import com.jim.multipos.ui.adminsignup.AdminSignUpActivity
import com.jim.multipos.ui.adminsignup.AdminSignUpModule
import com.jim.multipos.ui.adminsignup.fragment.confirmation.ConfirmationFragmentProvider
import com.jim.multipos.ui.adminsignup.fragment.general.GeneralFragmentProvider
import com.jim.multipos.ui.adminsignup.fragment.info.InfoFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        (AdminSignUpModule::class),
        (GeneralFragmentProvider::class),
        (InfoFragmentProvider::class),
        (ConfirmationFragmentProvider::class)
    ])
    abstract fun bindSignUpActivity(): AdminSignUpActivity

    @ContributesAndroidInjector(modules = [(AdminSignInModule::class)])
    abstract fun bindSignInActivity(): AdminSignInActivity

    @ContributesAndroidInjector(modules = [
        (AdminMainPageModule::class),
        (CompanyFragmentProvider::class),
        (DashboardFragmentProvider::class),
        (EstablishmentFragmentProvider::class),
        (ProductFragmentProvider::class),
        (ProductClassFragmentProvider::class)
    ])
    abstract fun bindAdminMainPageActivity(): AdminMainPageActivity
}