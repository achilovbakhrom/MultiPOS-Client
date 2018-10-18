package com.jim.multipos.environment.admin.ui.signin

import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.SignInRequest
import com.jim.multipos.utils.PrefsManager
import javax.inject.Inject

class SignInViewModel @Inject constructor(mDataManager: DataManager, val prefsManager: PrefsManager) : BaseViewModel(mDataManager){

    fun signIn(username: String, password: String, grantType: String, clientId: String, clientSecret: String){
        isLoading.value = true
        compositeDisposable
                .add(mDataManager.signIn(SignInRequest(username, password, grantType, clientId, clientSecret))
                .subscribe({
                    if (it.code == 200) {
                        prefsManager.putValue("accessToken", it?.data?.accessToken)
                        prefsManager.putValue("refreshToken", it?.data?.refreshToken)
                        prefsManager.putValue("refreshExpiresIn", it?.data?.refreshExpiresIn)
                        isLoading.value = false
                        errorMessage.value = null
                    } else {
                        isLoading.value = false
                        errorMessage.value = it.message ?: "Something went wrong!"
                    }
                },{
                    errorMessage.value = it.localizedMessage
                    isLoading.value = false
                }))
    }
}