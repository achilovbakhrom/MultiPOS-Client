package com.jim.multipos.di.module

import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftFragment
import com.jim.multipos.environment.admin.ui.company.main.CompanyFragment
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightFragment
import com.jim.multipos.environment.admin.ui.company.right.show.CompanyShowMainFragment
import com.jim.multipos.environment.admin.ui.company.right.show.bankRequisites.CompanyShowBankRequisitesFragment
import com.jim.multipos.environment.admin.ui.company.right.show.company.CompanyShowAboutFragment
import com.jim.multipos.environment.admin.ui.company.right.show.contactPerson.CompanyShowContactPersonFragment
import com.jim.multipos.environment.admin.ui.entities.EntitiesFragment
import com.jim.multipos.environment.admin.ui.entities.productClass.ProductClassFragment
import com.jim.multipos.environment.admin.ui.entities.productClass.productClassAddEdit.ProductClassAddEditFragment
import com.jim.multipos.environment.admin.ui.entities.productClass.productClassList.ProductClassListFragment
import com.jim.multipos.environment.admin.ui.establishment.EstablishmentFragment
import com.jim.multipos.environment.admin.ui.establishment.center.EstablishmentCenterFragment
import com.jim.multipos.environment.admin.ui.establishment.left.EstablishmentLeftFragment
import com.jim.multipos.environment.admin.ui.establishment.right.EstablishmentRightFragment
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
    abstract fun contributeCompanyShowAboutFragment(): CompanyShowAboutFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyShowContactPersonFragment(): CompanyShowContactPersonFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyShowBankRequisitesFragment(): CompanyShowBankRequisitesFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyShowMainFragment(): CompanyShowMainFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyFragment(): CompanyFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyLeftFragment(): CompanyLeftFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeCompanyRightFragment(): CompanyRightFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeEstablishmentFragment(): EstablishmentFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeEstablishmentCenterFragment(): EstablishmentCenterFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeEstablishmentLeftFragment(): EstablishmentLeftFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeEstablishmentRightFragment(): EstablishmentRightFragment

    @NotNull
    @ContributesAndroidInjector
    abstract fun contributeEntityFragment(): EntitiesFragment
}