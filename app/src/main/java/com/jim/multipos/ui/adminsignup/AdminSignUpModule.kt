package com.jim.multipos.ui.adminsignup

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.data.DataManager
import com.jim.multipos.ui.adminsignup.fragment.SignUpFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
class AdminSignUpModule {

    @Provides
    fun provideViewModelProvider(viewModel: AdminSignUpViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): AdminSignUpViewModel {
        return AdminSignUpViewModel(dataManager)
    }

}