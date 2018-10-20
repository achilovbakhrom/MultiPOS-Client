package com.jim.multipos.core

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.jim.multipos.core.managers.DataManager
import java.io.Serializable

abstract class SingleListViewModel<T: Serializable>(dataManager: DataManager): BaseViewModel(dataManager) {

    internal var page = ObservableField(0)

    var data: MutableLiveData<MutableList<T>> = MutableLiveData()

    fun refresh() {
        page.set(0)
        data.value?.clear()
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