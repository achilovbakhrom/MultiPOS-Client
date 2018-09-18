package com.jim.multipos.environment.admin.ui.signup

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.SignUp
import com.jim.multipos.environment.admin.ui.signup.model.SignUpModel
import com.jim.multipos.core.BaseViewModel
import com.jim.multipos.utils.Response

class AdminSignUpViewModel(mDataManager: DataManager) : BaseViewModel(mDataManager) {

    val response = MutableLiveData<Response<SignUp>>()
    val generalData = MutableLiveData<Response<SignUpModel>>()
    val infoData = MutableLiveData<Response<SignUpModel>>()

    fun getData(){
//        compositeDisposable.add(mDataManager.getData().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({it->response.value = Response.success(it)},
//                        {error->response.value = Response.error(error)}))
    }

    fun setGeneralData(model: SignUpModel){
        generalData.value = Response.success(model)
    }

    fun getGeneralData():LiveData<Response<SignUpModel>>{
        return generalData
    }

    fun setInfoData(model: SignUpModel){
        infoData.value = Response.success(model)
    }

    fun getInfoData():LiveData<Response<SignUpModel>>{
        return infoData
    }

    fun getResponse():LiveData<Response<SignUp>>{
        return response
    }

}