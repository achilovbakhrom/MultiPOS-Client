package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.di

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.viewmodel.EstablishmentViewModel
import dagger.Module
import dagger.Provides

@Module
class EstablishmentModule{

    @Provides
    fun provideViewModelProvider(viewModel: EstablishmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): EstablishmentViewModel {
        return EstablishmentViewModel(dataManager)
    }
}