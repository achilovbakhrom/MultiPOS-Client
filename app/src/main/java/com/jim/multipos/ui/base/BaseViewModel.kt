package com.jim.multipos.ui.base

import android.arch.lifecycle.ViewModel
import com.jim.multipos.data.DataManager
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(val mDataManager: DataManager) : ViewModel() {
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getDataManager():DataManager = mDataManager

}