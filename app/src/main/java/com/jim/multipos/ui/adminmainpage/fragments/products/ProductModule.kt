package com.jim.multipos.ui.adminmainpage.fragments.products

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.data.DataManager
import dagger.Module
import dagger.Provides

@Module
class ProductModule {

    @Provides
    fun provideViewModelProvider(viewModel: ProductViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): ProductViewModel {
        return ProductViewModel(dataManager)
    }
}