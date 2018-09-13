package com.jim.multipos.ui.adminsignup.fragment.info

import com.jim.multipos.ui.adminsignup.AdminSignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InfoFragmentProvider {

    @ContributesAndroidInjector(modules = [(AdminSignUpModule::class)])
    abstract fun provideInfoFragment(): InfoFragment
}