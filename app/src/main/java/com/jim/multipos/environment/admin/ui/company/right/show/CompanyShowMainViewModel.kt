package com.jim.multipos.environment.admin.ui.company.right.show

import android.arch.lifecycle.MutableLiveData
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.CompanyDTO
import javax.inject.Inject

class CompanyShowMainViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager) {

    var companyDTO = MutableLiveData<CompanyDTO>()



}