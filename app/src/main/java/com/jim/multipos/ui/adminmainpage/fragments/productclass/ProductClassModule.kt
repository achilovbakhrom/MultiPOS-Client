package com.jim.multipos.ui.adminmainpage.fragments.productclass

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.data.DataManager
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