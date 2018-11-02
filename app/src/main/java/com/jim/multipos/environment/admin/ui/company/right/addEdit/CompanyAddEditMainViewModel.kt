package com.jim.multipos.environment.admin.ui.company.right.addEdit

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.*
import javax.inject.Inject

class CompanyAddEditMainViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager) {

    var companyDTO: CompanyDTO? = null
    var saveCompanyAction: SingleLiveEvent<CompanyDTO> = SingleLiveEvent()

    override fun onViewCreated() {
        companyDTO = CompanyDTO()
        companyDTO?.company = Company()
    }

    fun saveCompany() {
        saveCompanyAction.call()
    }

    fun setAboutInformation(aboutInformation: AboutInformation?) {
        companyDTO?.company?.localPhotoPath = aboutInformation?.imagePath
        companyDTO?.company?.name = aboutInformation?.companyName ?: ""
        companyDTO?.company?.occupation = aboutInformation?.companyOccupation
        companyDTO?.company?.contactData = aboutInformation?.contactData
        companyDTO?.company?.description = aboutInformation?.description
    }

    fun setAddressInformation(addressInformation: AddressInformation?) {
        companyDTO?.company?.addressInformation = addressInformation
    }

    fun setContactPersonsInformation(contactPerson: List<CompanyContactPerson>? = null) {
        companyDTO?.companyContactPersons = contactPerson
    }

    fun setBankRequisitesInformation(bankRequisites: List<Requisite>? = null) {
        companyDTO?.requisites = bankRequisites
    }

}

