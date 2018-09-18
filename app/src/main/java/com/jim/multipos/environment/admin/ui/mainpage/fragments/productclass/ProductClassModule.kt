package com.jim.multipos.environment.admin.ui.mainpage.fragments.productclass

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class ProductClassModule {

    @Provides
    fun provideViewModelProvider(viewModel: ProductClassViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): ProductClassViewModel {
        return ProductClassViewModel(dataManager)
    }
}