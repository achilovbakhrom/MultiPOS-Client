package com.jim.multipos.ui.adminmainpage.fragments.dashboard.di

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.data.DataManager
import com.jim.multipos.ui.adminmainpage.fragments.dashboard.viewmodel.DashboardViewModel
import dagger.Module
import dagger.Provides

@Module
class DashboardModule {

    @Provides
    fun provideViewModelProvider(viewModel: DashboardViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): DashboardViewModel {
        return DashboardViewModel(dataManager)
    }
}