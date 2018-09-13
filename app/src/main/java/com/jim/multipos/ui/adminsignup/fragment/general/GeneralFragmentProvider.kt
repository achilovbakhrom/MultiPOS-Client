package com.jim.multipos.ui.adminsignup.fragment.general

import com.jim.multipos.ui.adminsignup.AdminSignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GeneralFragmentProvider {

    @ContributesAndroidInjector(modules = [(AdminSignUpModule::class)])
    abstract fun provideGeneralFragment(): GeneralFragment
}