package com.jim.multipos.environment.admin.ui.signin

import android.arch.lifecycle.MutableLiveData
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.MultiposResponseSingle
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.SignIn
import javax.inject.Inject

class SignInViewModel @Inject constructor(mDataManager: DataManager) : BaseViewModel(mDataManager){

    val signInLiveData= MutableLiveData<MultiposResponseSingle<SignIn>>()

    fun signIn(username: String, password: String, grant_type: String, client_id: String, client_secret: String){
        isLoading.value = true
        compositeDisposable.add(mDataManager.signIn(username, password, grant_type, client_id, client_secret)
                .subscribe({
            signInLiveData.value = it
            isLoading.value = false
        },{
            isError.value = true
            isLoading.value = false
        }))
    }
}