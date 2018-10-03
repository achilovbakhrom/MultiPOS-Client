package com.jim.multipos.environment.admin.ui.company

import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftFragment
import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftModule
import com.jim.multipos.environment.admin.ui.company.main.CompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightFragment
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightModule
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