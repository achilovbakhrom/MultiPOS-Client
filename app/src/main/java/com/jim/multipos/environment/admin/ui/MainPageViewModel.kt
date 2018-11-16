package com.jim.multipos.environment.admin.ui

import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.utils.PrefsManager
import com.jim.multipos.utils.TOKEN
import javax.inject.Inject

class MainPageViewModel @Inject constructor(mDataManager: DataManager, val prefsManager: PrefsManager) : BaseViewModel(mDataManager){

    init {
        prefsManager.putValue("accessToken", TOKEN)
    }
}