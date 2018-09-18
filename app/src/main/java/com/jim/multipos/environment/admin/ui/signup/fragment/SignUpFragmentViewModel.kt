package com.jim.multipos.environment.admin.ui.signup.fragment

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.SignUp
import com.jim.multipos.core.BaseViewModel


class SignUpFragmentViewModel(mDataManager: DataManager) : BaseViewModel(mDataManager){

    private val selected = MutableLiveData<SignUp>()

    fun select(item: SignUp) {
        selected.value = item
    }

    fun getSelected(): LiveData<SignUp> {
        return selected
    }
}