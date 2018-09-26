package com.jim.multipos.environment.admin.ui.mainpage.fragments.company

import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.left.CompanyLeftFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.left.CompanyLeftModule
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.main.CompanyFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.right.CompanyRightFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.company.right.CompanyRightModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CompanyFragmentProvider {

    @ContributesAndroidInjector(modules = [(CompanyModule::class)])
    abstract fun provideCompanyFragment(): CompanyFragment

    @ContributesAndroidInjector(modules = [(CompanyLeftModule::class)])
    abstract fun provideCompanyLeftFragment(): CompanyLeftFragment

    @ContributesAndroidInjector(modules = [(CompanyRightModule::class)])
    abstract fun provideCompanyRightFragment(): CompanyRightFragment
}