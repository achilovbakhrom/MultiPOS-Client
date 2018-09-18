package com.jim.multipos.environment.admin.ui.mainpage.fragments.productclass

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.core.BaseViewModel

class ProductClassViewModel(appDataManager: DataManager): BaseViewModel(appDataManager) {

    private val productClassItems = MutableLiveData<List<String>>()
    val productClassName = ObservableField<String>()
    val isEditable = ObservableBoolean(false)

    init {
        fetch()
    }

    private fun fetch(){
        val l = ArrayList<String>()
        l.add("asdsad123")
        l.add("asdsad456")
        l.add("asdsad789")
        l.add("asdsad000")
        productClassItems.value = l
    }

    fun getProductClassItems(): LiveData<List<String>> {
        return productClassItems
    }
}