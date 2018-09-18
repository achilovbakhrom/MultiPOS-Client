package com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.di

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.ui.mainpage.fragments.dashboard.viewmodel.DashboardViewModel
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