package com.jim.multipos.environment.admin.ui.entities

import com.jim.multipos.environment.admin.ui.company.CompanyModule
import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftFragment
import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftModule
import com.jim.multipos.environment.admin.ui.company.main.CompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightFragment
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class EntitiesFragmentProvider {

    @ContributesAndroidInjector(modules = [(EntityFragmentModule::class)])
    abstract fun provideEntityFragment(): EntitiesFragment

}