package com.jim.multipos.ui.adminmainpage

import com.jim.multipos.data.DataManager
import com.jim.multipos.ui.adminsignup.AdminSignUpViewModel
import dagger.Module
import dagger.Provides

@Module
class AdminMainPageModule {

    @Provides
    fun provideViewModel(dataManager: DataManager): AdminMainPageViewModel {
        return AdminMainPageViewModel(dataManager)
    }
}