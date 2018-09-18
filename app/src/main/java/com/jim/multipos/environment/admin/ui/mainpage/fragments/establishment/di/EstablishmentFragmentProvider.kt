package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.di

import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.EstablishmentFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class EstablishmentFragmentProvider {

    @ContributesAndroidInjector(modules = [(EstablishmentModule::class)])
    abstract fun provideEstablishmentFragment(): EstablishmentFragment
}