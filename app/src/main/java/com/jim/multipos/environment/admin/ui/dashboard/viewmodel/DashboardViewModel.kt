package com.jim.multipos.environment.admin.ui.dashboard.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.core.BaseViewModel

class DashboardViewModel(appDataManager: DataManager): BaseViewModel(appDataManager) {

    var posItems = MutableLiveData<List<String>>()
    var ordersItem = MutableLiveData<List<String>>()

    init {
        fetch()
    }

    private fun fetch() {
        val l = ArrayList<String>()
        l.add("asdsad123")
        l.add("asdsad456")
        l.add("asdsad789")
        l.add("asdsad000")
        posItems.value = l
        fetchOrders(l[0])
    }

    fun fetchOrders(pos: String){
        val l = ArrayList<String>()
        l.add("123$pos")
        l.add("456$pos")
        l.add("ssdas$pos")
        l.add("pplf$pos")
        ordersItem.value = l
    }

    fun getPosData(): LiveData<List<String>> {
        return posItems
    }

    fun getOrdersItem(): LiveData<List<String>>{
        return ordersItem
    }
}