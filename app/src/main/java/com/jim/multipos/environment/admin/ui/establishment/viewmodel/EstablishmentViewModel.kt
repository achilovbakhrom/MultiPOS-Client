package com.jim.multipos.environment.admin.ui.establishment.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.core.BaseViewModel

class EstablishmentViewModel(appDataManager: DataManager): BaseViewModel(appDataManager) {

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

    fun fetchPos(text: String){
        val l = ArrayList<String>()
        l.add("asdsad123$text")
        l.add("asdsad456$text")
        l.add("asdsad789$text")
        l.add("asdsad000$text")
        establishmentPosItems.value = l
    }

    fun getEstablishmentItems(): LiveData<List<String>>{
        return establishmentItems
    }

    fun getEstablishmentPosItems(): LiveData<List<String>>{
        return establishmentPosItems
    }
}