package com.jim.multipos.environment.admin.ui.dashboard.di

import com.jim.multipos.environment.admin.ui.dashboard.DashboardEstablishmentFragment
import com.jim.multipos.environment.admin.ui.dashboard.DashboardOrdersFragment
import com.jim.multipos.environment.admin.ui.dashboard.DashboardPosFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DashboardFragmentProvider {

    @ContributesAndroidInjector(modules = [(DashboardModule::class)])
    abstract fun provideDashboardEstablishmentFragment(): DashboardEstablishmentFragment

    @ContributesAndroidInjector(modules = [(DashboardModule::class)])
    abstract fun provideDashboardPosFragment(): DashboardPosFragment

    @ContributesAndroidInjector(modules = [(DashboardModule::class)])
    abstract fun provideDashboardOrdersFragment(): DashboardOrdersFragment
}