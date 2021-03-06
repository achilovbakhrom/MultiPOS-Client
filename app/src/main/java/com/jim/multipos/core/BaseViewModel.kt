package com.jim.multipos.core

import android.arch.lifecycle.ViewModel
import com.jim.multipos.core.managers.DataManager
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(val mDataManager: DataManager) : ViewModel() {
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getDataManager(): DataManager = mDataManager

}