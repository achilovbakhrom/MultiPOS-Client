package com.jim.multipos.environment.admin.ui.company.right.show.contactPerson

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.CompanyContactInformation
import java.util.ArrayList
import javax.inject.Inject

class CompanyShowContactPersonViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager){

    var contactInformation: CompanyContactInformation?=null
    var setContactPerson: SingleLiveEvent<String> = SingleLiveEvent()
    var fillContactPerson: SingleLiveEvent<String> = SingleLiveEvent()

    override fun onViewCreated() {
        if(contactInformation != null && contactInformation?.list?.size!!>0){
            fillContactPerson.call()
        }
        else {
            contactInformation = CompanyContactInformation(ArrayList())
        }
    }

    fun deliverDataToMainClass() {
        setContactPerson.call()
    }
}