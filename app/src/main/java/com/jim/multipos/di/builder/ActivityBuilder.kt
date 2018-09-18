package com.jim.multipos.di.builder

import com.jim.multipos.environment.admin.ui.mainpage.MainPageActivity
import com.jim.multipos.environment.admin.ui.mainpage.MainPageModule
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.CompanyFragmentProvider
import com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.di.DashboardFragmentProvider
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.di.EstablishmentFragmentProvider
import com.jim.multipos.environment.admin.ui.mainpage.fragments.productclass.ProductClassFragmentProvider
import com.jim.multipos.environment.admin.ui.mainpage.fragments.products.ProductFragmentProvider
import com.jim.multipos.environment.admin.ui.signin.AdminSignInActivity
import com.jim.multipos.environment.admin.ui.signin.AdminSignInModule
import com.jim.multipos.environment.admin.ui.signup.AdminSignUpActivity
import com.jim.multipos.environment.admin.ui.signup.AdminSignUpModule
import com.jim.multipos.environment.admin.ui.signup.fragment.confirmation.ConfirmationFragmentProvider
import com.jim.multipos.environment.admin.ui.signup.fragment.general.GeneralFragmentProvider
import com.jim.multipos.environment.admin.ui.signup.fragment.info.InfoFragmentProvider

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
        (MainPageModule::class),
        (CompanyFragmentProvider::class),
        (DashboardFragmentProvider::class),
        (EstablishmentFragmentProvider::class),
        (ProductFragmentProvider::class),
        (ProductClassFragmentProvider::class)
    ])
    abstract fun bindAdminMainPageActivity(): MainPageActivity
}