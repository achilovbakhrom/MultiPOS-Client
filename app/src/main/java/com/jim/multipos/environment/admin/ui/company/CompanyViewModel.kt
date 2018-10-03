package com.jim.multipos.environment.admin.ui.company

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.core.BaseViewModel


class CompanyViewModel(dataManager: DataManager): BaseViewModel(dataManager) {

    private val companyItems = MutableLiveData<List<String>>()

    val companyName = ObservableField<String>()
    val isEditable = ObservableBoolean(false)
    val title = ObservableField<String>()

    init {
        fetch()
    }

    private fun fetch(){
        val l = ArrayList<String>()
        l.add("asdsad123")
        l.add("asdsad456")
        l.add("asdsad789")
        l.add("asdsad000")
        companyItems.value = l
    }

    fun getLiveData():LiveData<List<String>>{
        return companyItems
    }
}