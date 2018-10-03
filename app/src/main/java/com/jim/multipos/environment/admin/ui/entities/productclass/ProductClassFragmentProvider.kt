package com.jim.multipos.environment.admin.ui.entities.productclass

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProductClassFragmentProvider {

    @ContributesAndroidInjector(modules = [(ProductClassModule::class)])
    abstract fun provideProductClassFragment(): ProductClassFragment
}