package com.jim.multipos.environment.admin.ui.mainpage.fragments.company

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CompanyFragmentProvider {

    @ContributesAndroidInjector(modules = [(CompanyModule::class)])
    abstract fun provideCompanyFragment(): CompanyFragment
}