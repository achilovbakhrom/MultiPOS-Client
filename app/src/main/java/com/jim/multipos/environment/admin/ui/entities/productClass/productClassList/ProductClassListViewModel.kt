package com.jim.multipos.environment.admin.ui.entities.productClass.productClassList


import com.jim.multipos.core.SingleListViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.utils.PrefsManager
import com.jim.multipos.utils.TOKEN
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class ProductClassListViewModel @Inject constructor(dataManager: DataManager, val prefsManager: PrefsManager): SingleListViewModel<ProductClass>(dataManager) {


    override fun onViewCreated() {
        data.value = mutableListOf()
        page.set(0)
        prefsManager.putValue("tenant_id", "nwFDL83")
        prefsManager.putValue("accessToken", TOKEN)
        load()
    }

    override fun load() {
        // mock
        isLoading.value = true
//        compositeDisposable.add(
//                Observable
//                        .timer(2, TimeUnit.SECONDS)
//                        .map {
//                            val list = mutableListOf<ProductClass>()
//                            for (i in 0..20) {
//                                val productClass = ProductClass()
//                                productClass.active = true
//                                productClass.name = "Product class $i"
//                                productClass.description = "Description $i"
//                                list.add(productClass)
//                            }
//                            list
//                        }
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe({
//                            isLoading.value = false
//                            val temp = data.value
//                            temp?.addAll(it)
//                            data.value = temp
//                        }, {
//                            isLoading.value = false
//                        })
//        )


        compositeDisposable.addAll(mDataManager.getProductClassList(page = page.get() ?: 0, pageSize = 20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value = it.data?.toMutableList()
                }, {
                    //todo
                    errorMessage.value = it.message
                }))
    }

}