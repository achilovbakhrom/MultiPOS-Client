package com.jim.multipos.environment.admin.ui.company.right.addEdit.about

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.AboutInformation
import com.jim.multipos.environment.admin.model.ContactData
import javax.inject.Inject

class AboutCompanyViewModel @Inject constructor(dataManager: DataManager): BaseViewModel(dataManager) {

    var aboutInformation: AboutInformation? = null

    var contactDataMap: MutableMap<Int, Map<Int, String>> = HashMap()

    var contactData: List<ContactData>? = null
        get() {
            clearContactDataMap()
            return if (!contactDataMap.isEmpty()) {
                val result = mutableListOf<ContactData>()
                val keys = contactDataMap.keys
                for (key in keys) {
                    val innerMap = contactDataMap[key]
                    val innerKeys = innerMap?.keys
                    for (innerKey in innerKeys!!) {
                        val contactData = ContactData()
                        contactData.type = innerKey
                        contactData.data = innerMap[innerKey]!!
                        result.add(contactData)
                    }
                }
                result.toList()
            } else
                null
        }

    var description: String? = null
    var companyNameErrorAction: SingleLiveEvent<String> = SingleLiveEvent()
    var businessOccupationErrorAction: SingleLiveEvent<String> = SingleLiveEvent()
    var setAboutAction: SingleLiveEvent<String> = SingleLiveEvent()
    var cancelAction: SingleLiveEvent<String> = SingleLiveEvent()
    var fillContentAction: SingleLiveEvent<String> = SingleLiveEvent()


    fun deliverDataToMainClass() {
        aboutInformation?.contactData = contactData
        setAboutAction.call()
    }

    override fun onViewCreated() {
        if (aboutInformation != null) { fillContentAction.call() } else {
            aboutInformation = AboutInformation()
        }
    }

    fun setAboutInformationAndNext(companyName: String, companyOccupation: String, description: String) {
        if (companyName.isEmpty()) {
            companyNameErrorAction.call()
            return
        }
        if (companyOccupation.isEmpty()) {
            businessOccupationErrorAction.call()
            return
        }
        aboutInformation?.companyName = companyName
        aboutInformation?.companyOccupation = companyOccupation
        aboutInformation?.description = description
        setAboutAction.call()
    }

    private fun clearContactDataMap() {
        val iterator = contactDataMap.entries.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            val innerMap = item.value
            val innerIterator = innerMap.entries.iterator()
            while (innerIterator.hasNext()) {
                val innerItem = innerIterator.next()
                if (innerItem.value.isNullOrEmpty()) {
                    iterator.remove()
                    break
                }
            }
        }
    }

    fun setImageLocalPath(localPath: String? = null) {
        aboutInformation?.imagePath = localPath
    }

    fun addNewContactData(tag: Int, type: Int, text: String) {
        val map = HashMap<Int, String>()
        map[type] = text
        contactDataMap[tag] = map
    }

    fun contactDataTextChanged(tag: Int, type: Int, text: String) {
        (contactDataMap[tag] as MutableMap<Int, String>)[type] = text
    }

    fun descriptionChanged(description: String) {
        aboutInformation?.description = description
    }

    fun cancelAdding() {
        cancelAction.call()
    }

}