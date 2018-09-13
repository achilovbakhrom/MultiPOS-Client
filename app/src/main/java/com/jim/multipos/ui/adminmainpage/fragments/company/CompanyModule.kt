package com.jim.multipos.ui.adminmainpage.fragments.company

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.data.DataManager
import dagger.Module
import dagger.Provides
import java.util.ArrayList

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