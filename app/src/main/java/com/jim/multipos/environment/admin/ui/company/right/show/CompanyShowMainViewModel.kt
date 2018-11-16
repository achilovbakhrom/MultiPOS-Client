package com.jim.multipos.environment.admin.ui.company.right.show

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.AboutInformation
import com.jim.multipos.environment.admin.model.AddressInformation
import com.jim.multipos.environment.admin.model.CompanyDTO
import javax.inject.Inject

class CompanyShowMainViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager) {

    var companyDTO = CompanyDTO()
    var aboutInformation: AboutInformation?=null
    var addressInformation: AddressInformation?=null

    fun setCompany(){
        companyDTO.company?.addressInformation = addressInformation
        companyDTO.company?.contactData = aboutInformation?.contactData
        companyDTO.company?.name = aboutInformation?.companyName!!
        companyDTO.company?.occupation = aboutInformation?.companyOccupation
        companyDTO.company?.description = aboutInformation?.description
    }

}