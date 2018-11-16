package com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.CompanyContactInformation
import com.jim.multipos.environment.admin.model.CompanyContactPerson
import java.util.ArrayList
import javax.inject.Inject

class ContactPersonAddEditViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager) {

    var contactInformation: CompanyContactPerson?=null
    var setContactPerson: SingleLiveEvent<String> = SingleLiveEvent()
    var fillContactPerson: SingleLiveEvent<String> = SingleLiveEvent()

    override fun onViewCreated() {
        if(contactInformation != null){
            fillContactPerson.call()
        }
    }

    fun deliverDataToMainClass() {
        setContactPerson.call()
    }

}