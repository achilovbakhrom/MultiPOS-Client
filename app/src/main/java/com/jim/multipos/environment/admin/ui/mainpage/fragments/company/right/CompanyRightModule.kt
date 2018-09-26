package com.jim.multipos.environment.admin.ui.mainpage.fragments.company.right

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class CompanyRightModule {

    @Provides
    fun provideLeftViewModelProvider(viewModel: CompanyRightViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideCompanyLeftViewModel(dataManager: DataManager): CompanyRightViewModel {
        return CompanyRightViewModel(dataManager)
    }
}