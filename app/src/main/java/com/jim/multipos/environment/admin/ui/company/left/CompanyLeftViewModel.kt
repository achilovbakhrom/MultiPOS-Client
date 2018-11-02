package com.jim.multipos.environment.admin.ui.company.left

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.jim.multipos.core.SingleListViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.AddressInformation
import com.jim.multipos.environment.admin.model.Company
import com.jim.multipos.environment.admin.model.CompanyDTO
import com.jim.multipos.environment.admin.model.ProductClass
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CompanyLeftViewModel @Inject constructor(dataManager: DataManager): SingleListViewModel<CompanyDTO>(dataManager) {

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
                            if (page.get() == 0) {
                                val list = mutableListOf<CompanyDTO>()
                                for (i in 0..20) {

                                    val company = Company(
                                            "Company Name $i",
                                            "Sale",
                                            addressInformation = AddressInformation(
                                                    "HOME",
                                                    "Buyuk Ipak Yo'li 160d",
                                                    city = "Tashkent",
                                                    country = "Uzbekistan"
                                            ),
                                            contactData = listOf(),
                                            description = ""
                                    )
                                    val companyDTO = CompanyDTO(
                                            company,
                                            listOf(),
                                            listOf()
                                    )
                                    list.add(companyDTO)
                                }
                                list
                            } else {
                                listOf<CompanyDTO>()
                            }

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
