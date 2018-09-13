package com.jim.multipos.ui.adminsignup.fragment.confirmation

import com.jim.multipos.ui.adminsignup.AdminSignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ConfirmationFragmentProvider {

    @ContributesAndroidInjector(modules = [(AdminSignUpModule::class)])
    abstract fun provideConfirmationFragment(): ConfirmationFragment
}