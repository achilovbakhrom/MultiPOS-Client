package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.di

import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.EstablishmentFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.center.EstablishmentCenterFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.center.EstablishmentCenterModule
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.left.EstablishmentLeftFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.left.EstablishmentLeftModule
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.right.EstablishmentRightFragment
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.right.EstablishmentRightModule
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