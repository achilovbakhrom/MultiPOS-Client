package com.jim.multipos.core

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.jim.multipos.core.managers.DataManager
import java.io.Serializable

abstract class SingleListViewModel<T: Serializable>(dataManager: DataManager): BaseViewModel(dataManager) {

    internal var page = ObservableField(0)

    var data: MutableLiveData<List<T>> = MutableLiveData()

    fun refresh() {
        page.set(0)
        load()
    }

    fun loadMore() {
        var tempPage = page.get()!!
        tempPage++
        page.set(tempPage)
        load()
    }

    abstract fun load()


}