package com.jim.multipos.environment.admin.ui.signup.fragment.info

import com.jim.multipos.environment.admin.ui.signup.SignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class InfoFragmentProvider {

    @ContributesAndroidInjector(modules = [(SignUpModule::class)])
    abstract fun provideInfoFragment(): InfoFragment
}