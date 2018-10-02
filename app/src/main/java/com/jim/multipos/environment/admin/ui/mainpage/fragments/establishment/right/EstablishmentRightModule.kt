package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.right

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class EstablishmentRightModule {

    @Provides
    fun provideLeftViewModelProvider(viewModel: EstablishmentRightViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideCompanyLeftViewModel(dataManager: DataManager): EstablishmentRightViewModel {
        return EstablishmentRightViewModel(dataManager)
    }
}