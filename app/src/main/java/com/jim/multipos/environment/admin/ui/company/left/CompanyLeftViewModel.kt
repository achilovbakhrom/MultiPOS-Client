package com.jim.multipos.environment.admin.ui.company.left

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.jim.multipos.core.SingleListViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.Company
import com.jim.multipos.environment.admin.model.ProductClass
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CompanyLeftViewModel @Inject constructor(dataManager: DataManager): SingleListViewModel<Company>(dataManager) {

    override fun onViewCreated() {
        data.value = mutableListOf()
        page.set(0)
        load()
    }

    override fun load() {
        isLoading.value = true
        compositeDisposable.add(
                Observable
                        .timer(2, TimeUnit.SECONDS)
                        .map {
                            val list = mutableListOf<Company>()
                            for (i in 0..10) {
                                val productClass = Company()
                                productClass.active = true
                                productClass.name = "Company $i"
                                productClass.description = "Description $i"
                                list.add(productClass)
                            }
                            list
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.value = false
                            val temp = data.value
                            temp?.addAll(it)
                            data.value = temp
                        }, {
                            isLoading.value = false
                        })
        )
    }

    private val companyItems = MutableLiveData<List<String>>()

    val companyName = ObservableField<String>()
    val isEditable = ObservableBoolean(false)
    val title = ObservableField<String>()

    init {
        fetch()
    }

    fun fetch(){
        val l = mutableListOf<String>()
        l.add("asdsad123")
        l.add("asdsad456")
        l.add("asdsad789")
        l.add("asdsad000")
        companyItems.value = l
    }

    fun getLiveData(): LiveData<List<String>> {
        return companyItems
    }
}
