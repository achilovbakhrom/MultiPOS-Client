package com.jim.multipos.environment.admin.ui.mainpage.fragments.products

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.core.BaseViewModel

class ProductViewModel(appDataManager: DataManager): BaseViewModel(appDataManager){

    private val productItems = MutableLiveData<List<String>>()
    val productName = ObservableField<String>()
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
        productItems.value = l
    }

    fun getProductItems(): LiveData<List<String>> {
        return productItems
    }
}