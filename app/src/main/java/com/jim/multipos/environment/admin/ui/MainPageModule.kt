package com.jim.multipos.environment.admin.ui

import com.jim.multipos.di.module.MainActivityFragmentBuildersModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainPageModule {

    @ContributesAndroidInjector(modules = [MainActivityFragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainPageActivity

}