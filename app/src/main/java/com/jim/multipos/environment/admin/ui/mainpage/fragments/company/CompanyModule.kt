package com.jim.multipos.environment.admin.ui.mainpage.fragments.company

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class CompanyModule {

    @Provides
    fun provideViewModelProvider(viewModel: CompanyViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): CompanyViewModel {
        return CompanyViewModel(dataManager)
    }

}