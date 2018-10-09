package com.jim.multipos.di.module

import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftFragment
import com.jim.multipos.environment.admin.ui.company.main.CompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightFragment
import com.jim.multipos.environment.admin.ui.entities.productclass.ProductClassFragment
import com.jim.multipos.environment.admin.ui.entities.productclass.productClassAddEdit.ProductClassAddEditFragment
import com.jim.multipos.environment.admin.ui.entities.productclass.productClassList.ProductClassListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import org.jetbrains.annotations.NotNull

@Suppress("unused")
@Module
abstract class MainActivityFragmentBuildersModule {

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeProductClassFragment(): ProductClassFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeProductClassListFragment(): ProductClassListFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeProductClassAddEditFragment(): ProductClassAddEditFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyFragment(): CompanyFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyLeftFragment(): CompanyLeftFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyRightFragment(): CompanyRightFragment

}