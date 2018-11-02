package com.jim.multipos.di.module


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.core.EmptyViewModel
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.di.ViewModelKey
import com.jim.multipos.environment.admin.model.CompanyDTO
import com.jim.multipos.environment.admin.ui.MainPageViewModel
import com.jim.multipos.environment.admin.ui.SearchViewModel
import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftViewModel
import com.jim.multipos.environment.admin.ui.company.left.CompanyViewHolder
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightViewModel
import com.jim.multipos.environment.admin.ui.company.right.addEdit.CompanyAddEditMainViewModel
import com.jim.multipos.environment.admin.ui.company.right.addEdit.about.AboutCompanyViewModel
import com.jim.multipos.environment.admin.ui.company.right.addEdit.address.AddressCompanyViewModel
import com.jim.multipos.environment.admin.ui.company.right.addEdit.bankRequisites.BankRequisitesViewModel
import com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson.ContactPersonViewModel
import com.jim.multipos.environment.admin.ui.company.right.show.CompanyShowMainViewModel
import com.jim.multipos.environment.admin.ui.company.right.show.bankRequisites.CompanyShowRequisitesViewModel
import com.jim.multipos.environment.admin.ui.company.right.show.company.CompanyShowAboutViewModel
import com.jim.multipos.environment.admin.ui.company.right.show.contactPerson.CompanyShowContactPersonViewModel
import com.jim.multipos.environment.admin.ui.dashboard.viewmodel.DashboardViewModel
import com.jim.multipos.environment.admin.ui.entities.productClass.productClassAddEdit.ProductClassAddEditViewModel
import com.jim.multipos.environment.admin.ui.entities.productClass.productClassList.ProductClassListViewModel
import com.jim.multipos.environment.admin.ui.establishment.center.EstablishmentCenterViewModel
import com.jim.multipos.environment.admin.ui.establishment.left.EstablishmentLeftViewModel
import com.jim.multipos.environment.admin.ui.establishment.right.EstablishmentRightViewModel
import com.jim.multipos.environment.admin.ui.signin.SignInViewModel
import com.jim.multipos.environment.admin.ui.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductClassListViewModel::class)
    abstract fun bindProductClassListViewModel(productClassListViewModel: ProductClassListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductClassAddEditViewModel::class)
    abstract fun bindProductClassAddEditViewModel(productClassAddEditViewModel: ProductClassAddEditViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    abstract fun bindSignInViewModel(signInViewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainPageViewModel::class)
    abstract fun bindMainPageViewModel(mainPageViewModel: MainPageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompanyLeftViewModel::class)
    abstract fun bindCompanyLeftViewModel(companyLeftViewModel: CompanyLeftViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompanyRightViewModel::class)
    abstract fun bindCompanyRightViewModel(companyRightViewModel: CompanyRightViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EstablishmentCenterViewModel::class)
    abstract fun bindEstablishmentCenterViewModel(establishmentCenterViewModel: EstablishmentCenterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EstablishmentLeftViewModel::class)
    abstract fun bindEstablishmentLeftViewModel(establishmentLeftViewModel: EstablishmentLeftViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EstablishmentRightViewModel::class)
    abstract fun bindEstablishmentRightViewModel(establishmentRightViewModel: EstablishmentRightViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    abstract fun bindEmptyViewModel(emptyViewModel: EmptyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompanyShowMainViewModel::class)
    abstract fun bindCompanyShowMainViewModel(companyShowMainViewModel: CompanyShowMainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompanyShowContactPersonViewModel::class)
    abstract fun bindCompanyShowContactPersonViewModel(companyShowContactPersonViewModel: CompanyShowContactPersonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompanyShowAboutViewModel::class)
    abstract fun bindCompanyShowAboutViewModel(companyShowAboutViewModel: CompanyShowAboutViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompanyShowRequisitesViewModel::class)
    abstract fun bindCompanyShowRequisitesViewModel(companyShowRequisitesViewModel: CompanyShowRequisitesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutCompanyViewModel::class)
    abstract fun bindAboutCompanyViewModel(aboutCompanyViewModel: AboutCompanyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddressCompanyViewModel::class)
    abstract fun bindAddressCompanyViewModel(addressCompanyViewModel: AddressCompanyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContactPersonViewModel::class)
    abstract fun bindContactPersonViewModel(contactPersonViewModel: ContactPersonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BankRequisitesViewModel::class)
    abstract fun bindBankRequisitesViewModel(bankRequisitesViewModel: BankRequisitesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompanyAddEditMainViewModel::class)
    abstract fun bindCompanyAddEditMainViewModel(companyAddEditMainViewModel: CompanyAddEditMainViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}