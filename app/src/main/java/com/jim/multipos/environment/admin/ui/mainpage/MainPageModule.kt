package com.jim.multipos.environment.admin.ui.mainpage

import com.jim.multipos.core.managers.DataManager
import dagger.Module
import dagger.Provides

@Module
class MainPageModule {

    @Provides
    fun provideViewModel(dataManager: DataManager): MainPageViewModel {
        return MainPageViewModel(dataManager)
    }
}