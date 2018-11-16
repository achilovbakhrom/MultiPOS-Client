package com.jim.multipos.environment.admin.ui.company.right.addEdit.contactPerson

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.CompanyContactInformation
import com.jim.multipos.environment.admin.model.CompanyContactPerson
import java.util.ArrayList
import javax.inject.Inject

class ContactPersonViewModel @Inject constructor(dataManager: DataManager) : BaseViewModel(dataManager) {

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

    fun setContactInfo(info: CompanyContactInformation){
        contactInformation = info
    }

    fun addContactInfoItem(item: CompanyContactPerson){
        contactInformation?.list?.add(item)
    }

    fun editContactInfoItem(item: CompanyContactPerson, position: Int){
        contactInformation?.list?.set(position, item)
    }

    fun deliverDataToMainClass() {
        setContactPerson.call()
    }
}