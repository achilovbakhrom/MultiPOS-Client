package com.jim.multipos.di.module


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.core.ViewModelProviderFactory
import com.jim.multipos.di.ViewModelKey
import com.jim.multipos.environment.admin.ui.SearchViewModel
import com.jim.multipos.environment.admin.ui.company.left.CompanyLeftViewModel
import com.jim.multipos.environment.admin.ui.company.right.CompanyRightViewModel
import com.jim.multipos.environment.admin.ui.entities.productclass.productClassAddEdit.ProductClassAddEditViewModel
import com.jim.multipos.environment.admin.ui.entities.productclass.productClassList.ProductClassListViewModel
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
    @ViewModelKey(CompanyLeftViewModel::class)
    abstract fun bindSignUpViewModel(companyLeftViewModel: CompanyLeftViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompanyRightViewModel::class)
    abstract fun bindSignUpViewModel(companyRightViewModel: CompanyRightViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSignUpViewModel(searchViewModel: SearchViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

}