package com.jim.multipos.di.builder

import com.jim.multipos.di.module.MainActivityFragmentBuildersModule
import com.jim.multipos.di.module.SignUpActivityFragmentBuildersModule
import com.jim.multipos.environment.admin.ui.MainPageActivity
import com.jim.multipos.environment.admin.ui.signin.SignInActivity
import com.jim.multipos.environment.admin.ui.signup.SignUpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [ SignUpActivityFragmentBuildersModule::class])
    abstract fun bindSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    abstract fun bindSignInActivity(): SignInActivity

    @ContributesAndroidInjector(modules = [MainActivityFragmentBuildersModule::class])
    abstract fun bindAdminMainPageActivity(): MainPageActivity

}