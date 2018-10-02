package com.jim.multipos.environment.admin.ui.mainpage.fragments.establishment.center

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.managers.DataManager

class EstablishmentCenterViewModel(dataManager: DataManager): BaseViewModel(dataManager){

    private val establishmentPosItems = MutableLiveData<List<String>>()
    val establishmentPosName = ObservableField<String>()

    fun fetch(){
        val l = ArrayList<String>()
        l.add("asdsad123")
        l.add("asdsad456")
        l.add("asdsad789")
        l.add("asdsad000")
        establishmentPosItems.value = l
    }

    fun getEstablishmentItems(): LiveData<List<String>> {
        return establishmentPosItems
    }
}