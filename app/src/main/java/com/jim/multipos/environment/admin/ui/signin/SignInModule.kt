package com.jim.multipos.environment.admin.ui.signin

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class SignInModule {
    @ContributesAndroidInjector
    abstract fun contributeSignInActivity(): SignInActivity
}