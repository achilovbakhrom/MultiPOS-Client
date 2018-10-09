package com.jim.multipos.environment.admin.ui.signup

import com.jim.multipos.di.module.SignUpActivityFragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class SignUpModule {

    @ContributesAndroidInjector(modules = [SignUpActivityFragmentBuildersModule::class])
    abstract fun contributeSignUpActivity(): SignUpActivity

}