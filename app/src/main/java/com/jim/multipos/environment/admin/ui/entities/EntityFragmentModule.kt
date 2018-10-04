package com.jim.multipos.environment.admin.ui.entities

import android.arch.lifecycle.ViewModelProvider
import com.jim.multipos.ViewModelProviderFactory
import com.jim.multipos.core.EmptyViewModel
import com.jim.multipos.core.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class EntityFragmentModule {

    @Provides
    fun provideViewModelProvider(viewModel: EmptyViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory<Any>(viewModel)
    }

    @Provides
    fun provideMainViewModel(dataManager: DataManager): EmptyViewModel {
        return EmptyViewModel(dataManager)
    }

}