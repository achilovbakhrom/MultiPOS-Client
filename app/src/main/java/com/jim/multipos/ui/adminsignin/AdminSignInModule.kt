package com.jim.multipos.ui.adminsignin

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class AdminSignInModule {

    @Provides
    fun provideViewModelProvider(viewModel: AdminSignInViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): AdminSignInViewModel {
        return AdminSignInViewModel(dataManager)
    }
}