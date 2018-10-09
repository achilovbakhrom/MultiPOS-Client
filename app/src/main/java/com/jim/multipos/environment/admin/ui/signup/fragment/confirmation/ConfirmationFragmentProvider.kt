package com.jim.multipos.environment.admin.ui.signup.fragment.confirmation

import com.jim.multipos.environment.admin.ui.signup.SignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ConfirmationFragmentProvider {

    @ContributesAndroidInjector(modules = [(SignUpModule::class)])
    abstract fun provideConfirmationFragment(): ConfirmationFragment
}