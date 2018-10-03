package com.jim.multipos.environment.admin.ui.establishment.center

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.ui.establishment.center.EstablishmentCenterViewModel
import dagger.Module
import dagger.Provides

@Module
class EstablishmentCenterModule{

    @Provides
    fun provideLeftViewModelProvider(viewModel: EstablishmentCenterViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideCompanyLeftViewModel(dataManager: DataManager): EstablishmentCenterViewModel {
        return EstablishmentCenterViewModel(dataManager)
    }
}