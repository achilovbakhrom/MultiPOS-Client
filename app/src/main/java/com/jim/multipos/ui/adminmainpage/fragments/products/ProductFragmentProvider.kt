package com.jim.multipos.ui.adminmainpage.fragments.products

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProductFragmentProvider {

    @ContributesAndroidInjector(modules = [(ProductModule::class)])
    abstract fun provideProductFragment(): ProductFragment
}