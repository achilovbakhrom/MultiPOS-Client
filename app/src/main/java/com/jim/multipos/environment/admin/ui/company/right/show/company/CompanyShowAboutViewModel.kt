package com.jim.multipos.environment.admin.ui.company.right.show.company

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.Company
import javax.inject.Inject

class CompanyShowAboutViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager) {

    var company: Company?=null


}