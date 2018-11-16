package com.jim.multipos.environment.admin.ui.company.right.addEdit.bankRequisites

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.CompanyRequisiteInformation
import com.jim.multipos.environment.admin.model.Requisite
import com.jim.multipos.environment.admin.model.RequisiteItem
import java.text.ParsePosition
import javax.inject.Inject

class BankRequisitesViewModel @Inject constructor(dataManager: DataManager) : BaseViewModel(dataManager) {

    var fillBankRequisite: SingleLiveEvent<String> = SingleLiveEvent()
    var companyRequisiteInfo: CompanyRequisiteInformation?=null
    var chips: ArrayList<RequisiteItem> = ArrayList()
    var setBankRequisites: SingleLiveEvent<String> = SingleLiveEvent()

    override fun onViewCreated() {
        if(companyRequisiteInfo!=null&&companyRequisiteInfo?.list?.size!!>0){
            fillBankRequisite.call()
        }else {
            companyRequisiteInfo = CompanyRequisiteInformation(ArrayList())
        }
    }

    fun addRequisite(requisite: Requisite){
        companyRequisiteInfo?.list?.add(requisite)
    }

    fun editRequisite(requisite: Requisite, position: Int){
        companyRequisiteInfo?.list?.set(position, requisite)
    }

    fun deliverDataToMainClass(){
        setBankRequisites.call()
    }
}