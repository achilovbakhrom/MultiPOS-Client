package com.jim.multipos.environment.admin.ui.establishment.left

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.ui.establishment.left.EstablishmentLeftViewModel
import dagger.Module
import dagger.Provides

@Module
class EstablishmentLeftModule {

    @Provides
    fun provideLeftViewModelProvider(viewModel: EstablishmentLeftViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideCompanyLeftViewModel(dataManager: DataManager): EstablishmentLeftViewModel {
        return EstablishmentLeftViewModel(dataManager)
    }
}