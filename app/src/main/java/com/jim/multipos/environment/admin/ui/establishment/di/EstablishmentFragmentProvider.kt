package com.jim.multipos.environment.admin.ui.establishment.di

import com.jim.multipos.environment.admin.ui.establishment.EstablishmentFragment
import com.jim.multipos.environment.admin.ui.establishment.center.EstablishmentCenterFragment
import com.jim.multipos.environment.admin.ui.establishment.center.EstablishmentCenterModule
import com.jim.multipos.environment.admin.ui.establishment.left.EstablishmentLeftFragment
import com.jim.multipos.environment.admin.ui.establishment.left.EstablishmentLeftModule
import com.jim.multipos.environment.admin.ui.establishment.right.EstablishmentRightFragment
import com.jim.multipos.environment.admin.ui.establishment.right.EstablishmentRightModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class EstablishmentFragmentProvider {

    @ContributesAndroidInjector(modules = [(EstablishmentModule::class)])
    abstract fun provideEstablishmentFragment(): EstablishmentFragment

    @ContributesAndroidInjector(modules = [(EstablishmentLeftModule::class)])
    abstract fun provideEstablishmentLeftFragment(): EstablishmentLeftFragment

    @ContributesAndroidInjector(modules = [(EstablishmentCenterModule::class)])
    abstract fun provideEstablishmentCenterFragment(): EstablishmentCenterFragment

    @ContributesAndroidInjector(modules = [(EstablishmentRightModule::class)])
    abstract fun provideEstablishmentRightFragment(): EstablishmentRightFragment
}