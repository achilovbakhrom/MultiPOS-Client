package com.jim.multipos.environment.admin.ui.company

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.ui.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
class CompanyModule {

    @Provides
    fun provideViewModelProvider(viewModel: SearchViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): SearchViewModel {
        return SearchViewModel(dataManager)
    }

}