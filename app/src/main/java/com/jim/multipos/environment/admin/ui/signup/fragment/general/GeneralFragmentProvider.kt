package com.jim.multipos.environment.admin.ui.signup.fragment.general

import com.jim.multipos.environment.admin.ui.signup.SignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GeneralFragmentProvider {

    @ContributesAndroidInjector(modules = [(SignUpModule::class)])
    abstract fun provideGeneralFragment(): GeneralFragment
}