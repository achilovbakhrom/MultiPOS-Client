package com.jim.multipos.core

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Bundle
import com.jim.multipos.core.managers.DataManager
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(val mDataManager: DataManager) : ViewModel() {
    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getDataManager(): DataManager = mDataManager

    open fun onSaveInstanceState(outState: Bundle) {}

    open fun onViewCreated() {}


}