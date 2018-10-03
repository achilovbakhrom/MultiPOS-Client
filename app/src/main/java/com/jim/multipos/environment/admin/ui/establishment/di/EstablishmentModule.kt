package com.jim.multipos.environment.admin.ui.establishment.di

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.ui.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
class EstablishmentModule{

    @Provides
    fun provideViewModelProvider(viewModel: SearchViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): SearchViewModel {
        return SearchViewModel(dataManager)
    }
}