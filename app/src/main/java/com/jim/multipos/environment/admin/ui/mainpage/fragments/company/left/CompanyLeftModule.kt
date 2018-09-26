package com.jim.multipos.environment.admin.ui.mainpage.fragments.company.left

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class CompanyLeftModule{

    @Provides
    fun provideLeftViewModelProvider(viewModel: CompanyLeftViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideCompanyLeftViewModel(dataManager: DataManager): CompanyLeftViewModel{
        return CompanyLeftViewModel(dataManager)
    }
}