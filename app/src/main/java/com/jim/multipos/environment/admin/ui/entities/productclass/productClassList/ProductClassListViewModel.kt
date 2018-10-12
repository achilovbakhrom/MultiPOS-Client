package com.jim.multipos.environment.admin.ui.entities.productclass.productClassList

import com.jim.multipos.core.SingleListViewModel
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.environment.admin.model.ProductClass
import javax.inject.Inject

class ProductClassListViewModel @Inject constructor(dataManager: DataManager): SingleListViewModel<ProductClass>(dataManager) {


    override fun onViewCreated() {
        data.value = mutableListOf()
        page.set(0)
        load()
    }

    override fun load() {
        compositeDisposable.add(
            mDataManager
                .getProductClassList(page.get()!!, 20)
                .subscribe({

                    val tempData = mutableListOf<ProductClass>()
                    tempData.addAll(data.value!!)
                    tempData.addAll(it.data!!)
                    data.value = tempData

                }, {
                    it.printStackTrace()
                })

        )
    }


}