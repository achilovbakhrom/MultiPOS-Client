package com.jim.multipos.environment.admin.ui.entities.products

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProductFragmentProvider {

    @ContributesAndroidInjector(modules = [(ProductModule::class)])
    abstract fun provideProductFragment(): ProductFragment
}