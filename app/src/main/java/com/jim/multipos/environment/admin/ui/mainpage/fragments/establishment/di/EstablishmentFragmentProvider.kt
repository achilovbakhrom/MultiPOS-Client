package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.di

import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.EstablishmentFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.left.EstablishmentLeftFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.left.EstablishmentLeftModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class EstablishmentFragmentProvider {

    @ContributesAndroidInjector(modules = [(EstablishmentModule::class)])
    abstract fun provideEstablishmentFragment(): EstablishmentFragment

    @ContributesAndroidInjector(modules = [(EstablishmentLeftModule::class)])
    abstract fun provideEstablishmentLeftFragment(): EstablishmentLeftFragment

}