package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.left

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.managers.DataManager

class EstablishmentLeftViewModel(dataManager: DataManager): BaseViewModel(dataManager) {

    private val establishmentItems = MutableLiveData<List<String>>()
    private val establishmentPosItems = MutableLiveData<List<String>>()

    val establishmentName = ObservableField<String>()
    val posName = ObservableField<String>()
    val isEditable = ObservableBoolean(false)
    val isPosMode = ObservableBoolean(false)

    init {
        fetch()
    }

    private fun fetch(){
        val l = ArrayList<String>()
        l.add("asdsad123")
        l.add("asdsad456")
        l.add("asdsad789")
        l.add("asdsad000")
        establishmentItems.value = l
    }

    fun getEstablishmentItems(): LiveData<List<String>> {
        return establishmentItems
    }

}