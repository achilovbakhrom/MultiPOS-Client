package com.jim.multipos.environment.admin.ui.signup.fragment.general

import com.jim.multipos.environment.admin.ui.signup.AdminSignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GeneralFragmentProvider {

    @ContributesAndroidInjector(modules = [(AdminSignUpModule::class)])
    abstract fun provideGeneralFragment(): GeneralFragment
}