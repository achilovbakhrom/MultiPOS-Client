package com.jim.multipos.di.module

import com.jim.multipos.environment.admin.ui.signup.fragment.confirmation.ConfirmationFragment
import com.jim.multipos.environment.admin.ui.signup.fragment.general.GeneralFragment
import com.jim.multipos.environment.admin.ui.signup.fragment.info.InfoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.jetbrains.annotations.NotNull


@Suppress("unused")
@Module
abstract class SignUpActivityFragmentBuildersModule {

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeConfirmationFragment(): ConfirmationFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeGeneralFragment(): GeneralFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeInfoFragment(): InfoFragment

}