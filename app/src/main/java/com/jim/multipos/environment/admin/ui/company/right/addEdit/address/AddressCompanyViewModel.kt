package com.jim.multipos.environment.admin.ui.company.right.addEdit.address

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.UiThread
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.AddressInformation
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AddressCompanyViewModel @Inject constructor(dataManager: DataManager) : BaseViewModel(dataManager) {

    var addressInformation: AddressInformation? = null

    var fillContentAction: SingleLiveEvent<String> = SingleLiveEvent()
    var setAddressInformationAction: SingleLiveEvent<String> = SingleLiveEvent()
    var countryListLiveData: MutableLiveData<List<Country>> = MutableLiveData()

    var cityListLiveData: MutableLiveData<List<String>> = MutableLiveData()

    override fun onViewCreated() {
        if (countryListLiveData.value == null || countryListLiveData.value?.isEmpty() == true) {
            isLoading.value = true
            loadCountryList()
        } else {
            initAddressInformation()
        }
    }

    fun initAddressInformation() {
        if (addressInformation == null) {
            addressInformation = AddressInformation()
        } else {
            fillContentAction.call()
        }
    }

    private fun loadCountryList() {
        // mock data
        compositeDisposable.add(
                Observable
                        .timer(2, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            val uzbekistan = Country("Uzbekistan", listOf("Tashkent", "Sirdarya", "Jizzakh", "Samarkand", "Bukhara", "Navoi", "etc."))
                            val usa = Country("USA", listOf("Arizona", "New York", "Washington", "New Orlean", "etc."))
                            val russianFederation = Country("Russian Federation", listOf("Moscow", "St.Petersburg", "Kaliningrad", "Orengurg", "Vladivostok", "etc."))
                            val tajikistan = Country("Tajikistan", listOf("Region 1", "Region 2", "Region 3", "Region 4", "etc."))
                            countryListLiveData.value = listOf(uzbekistan, usa, russianFederation, tajikistan)
                            isLoading.value = false
                        }, {

                        })
        )
        // mock data end
    }

    fun selectCountry(pos: Int) {
        addressInformation?.country = countryListLiveData.value!![pos].name
        cityListLiveData.value = countryListLiveData.value!![pos].cityList
    }

    fun selectCity(pos: Int) {
        addressInformation?.city = cityListLiveData.value?.get(pos)
    }

    fun deliverDataToMainClass() {
        setAddressInformationAction.call()
    }

}

data class Country(val name: String, val cityList: List<String> = listOf())