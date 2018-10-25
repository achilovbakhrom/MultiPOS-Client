package com.jim.multipos.environment.admin.ui.entities.productClass.productClassAddEdit

import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import com.jim.multipos.R
import com.jim.multipos.core.SingleLiveEvent
import com.jim.multipos.core.fragments.baseAddEditFragment.BaseAddEditViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.ProductClass
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ProductClassAddEditViewModel  @Inject constructor(mDataManager: DataManager, var resources: Resources): BaseAddEditViewModel<ProductClass>(mDataManager) {

    var updatedEvent = SingleLiveEvent<ProductClass>()
    var savedEvent = SingleLiveEvent<ProductClass>()
    var nameErrorEvent: MutableLiveData<String> = MutableLiveData()
    var addedItem: MutableLiveData<ProductClass> = MutableLiveData()

    fun cancelEvent(productClass: ProductClass) {
        if (item == null) {


        } else {

        }
    }

    fun saveEvent(productClass: ProductClass) {
        if (productClass.name.isEmpty()) {
            nameErrorEvent.value = resources.getString(R.string.product_class_name_error)
            return
        }

        if (item == null) { //add mode
            isLoading.value = true
            compositeDisposable.add(
                Observable
                        .timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            addedItem.value = productClass
                            isLoading.value = false
                            savedEvent.call()
                        }
            )
        } else { //edit mode
            isLoading.value = true
            compositeDisposable.add(
                Observable
                        .timer(2, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            isLoading.value = false
                            updatedEvent.call()
                        }
            )
        }
    }

}