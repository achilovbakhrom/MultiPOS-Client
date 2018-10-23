package com.jim.multipos.environment.admin.ui.entities.productClass.productClassList


import com.jim.multipos.core.SingleListViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.ProductClass
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ProductClassListViewModel @Inject constructor(dataManager: DataManager): SingleListViewModel<ProductClass>(dataManager) {


    override fun onViewCreated() {
        data.value = mutableListOf()
        page.set(0)
        load()
    }

    override fun load() {

        // mock
        isLoading.value = true
        compositeDisposable.add(
                Observable
                        .timer(2, TimeUnit.SECONDS)
                        .map {
                            val list = mutableListOf<ProductClass>()
                            for (i in 0..10) {
                                val productClass = ProductClass()
                                productClass.active = true
                                productClass.name = "Product class $i"
                                productClass.description = "Description $i"
                                list.add(productClass)
                            }
                            list
                        }
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            isLoading.value = false
                            val temp = data.value
                            temp?.addAll(it)
                            data.value = temp
                        }, {
                            isLoading.value = false
                        })
        )


//        val timer = Timer()
//        timer.schedule(object : TimerTask() {
//            override fun run() {
//                val list = mutableListOf<ProductClass>()
//                for (i in 0..10) {
//                    val productClass = ProductClass()
//                    productClass.active = true
//                    productClass.name = "Product class $i"
//                    productClass.description = "Description $i"
//                    list.add(productClass)
//                }
//                isLoading.postValue(true)
//                data.postValue(list)
//            }
//        }, 3000)
        // mock end

//        compositeDisposable.add(
//            mDataManager
//                .getProductClassList(page.get()!!, 20)
//                .subscribe({
//
//                    val tempData = mutableListOf<ProductClass>()
//                    tempData.addAll(data.value!!)
//                    tempData.addAll(it.data!!)
//                    data.value = tempData
//
//                }, {
//                    it.printStackTrace()
//                })
//
//        )
    }

}