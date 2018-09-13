package com.jim.multipos.ui.adminsignup.fragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jim.multipos.data.DataManager
import com.jim.multipos.data.model.adminsignup.AdminSignUpModel
import com.jim.multipos.ui.base.BaseViewModel


class SignUpFragmentViewModel(mDataManager: DataManager) : BaseViewModel(mDataManager){

    private val selected = MutableLiveData<AdminSignUpModel>()

    fun select(item: AdminSignUpModel) {
        selected.value = item
    }

    fun getSelected(): LiveData<AdminSignUpModel> {
        return selected
    }
}