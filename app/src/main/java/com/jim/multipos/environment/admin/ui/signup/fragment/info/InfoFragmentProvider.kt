package com.jim.multipos.environment.admin.ui.signup.fragment.info

import com.jim.multipos.environment.admin.ui.signup.AdminSignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InfoFragmentProvider {

    @ContributesAndroidInjector(modules = [(AdminSignUpModule::class)])
    abstract fun provideInfoFragment(): InfoFragment
}