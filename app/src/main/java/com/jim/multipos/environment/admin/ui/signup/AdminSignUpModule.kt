package com.jim.multipos.environment.admin.ui.signup

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
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