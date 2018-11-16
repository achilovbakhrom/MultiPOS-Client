package com.jim.multipos.environment.admin.ui.company.right.show.bankRequisites

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.CompanyContactInformation
import com.jim.multipos.environment.admin.model.CompanyRequisiteInformation
import java.util.ArrayList
import javax.inject.Inject

class CompanyShowRequisitesViewModel @Inject constructor(dataManager: DataManager) : BaseViewModel(dataManager){

    var requisiteInformation: CompanyRequisiteInformation?=null
    var setRequisite: SingleLiveEvent<String> = SingleLiveEvent()
    var fillRequisite: SingleLiveEvent<String> = SingleLiveEvent()

    override fun onViewCreated() {
        if(requisiteInformation != null && requisiteInformation?.list?.size!!>0){
            fillRequisite.call()
        }
        else {
            requisiteInformation = CompanyRequisiteInformation(ArrayList())
        }
    }
}