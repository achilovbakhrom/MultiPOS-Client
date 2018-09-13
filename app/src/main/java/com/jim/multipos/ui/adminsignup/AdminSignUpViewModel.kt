package com.jim.multipos.ui.adminsignup

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jim.multipos.data.DataManager
import com.jim.multipos.data.model.adminsignup.AdminSignUpModel
import com.jim.multipos.ui.adminsignup.model.SignUpModel
import com.jim.multipos.ui.base.BaseViewModel
import com.jim.multipos.utils.Response
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AdminSignUpViewModel(mDataManager: DataManager) : BaseViewModel(mDataManager) {

    val response = MutableLiveData<Response<AdminSignUpModel>>()
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

    fun getResponse():LiveData<Response<AdminSignUpModel>>{
        return response
    }

}