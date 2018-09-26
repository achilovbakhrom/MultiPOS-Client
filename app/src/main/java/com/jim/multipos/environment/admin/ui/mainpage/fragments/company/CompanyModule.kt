package com.jim.multipos.environment.admin.ui.mainpage.fragments.company

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.ui.mainpage.MainPageViewModel
import com.jim.multipos.environment.admin.ui.mainpage.SearchViewModel
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