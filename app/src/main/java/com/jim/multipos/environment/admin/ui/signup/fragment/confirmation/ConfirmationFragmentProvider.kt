package com.jim.multipos.environment.admin.ui.signup.fragment.confirmation

import com.jim.multipos.environment.admin.ui.signup.AdminSignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ConfirmationFragmentProvider {

    @ContributesAndroidInjector(modules = [(AdminSignUpModule::class)])
    abstract fun provideConfirmationFragment(): ConfirmationFragment
}