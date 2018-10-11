package com.jim.multipos.core.managers

import com.jim.multipos.core.MultiposResponseList
import com.jim.multipos.core.MultiposResponseSingle
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.environment.admin.model.SignUp
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(var service: NetworkService) : DataManager {
    override fun getProductClassList(page: Int, pageSize: Int, token: String, tenantId: String): Single<MultiposResponseList<ProductClass>> {

        val a = 5
        return service
                .getProductClassList(page = page, pageSize = pageSize, token = token, tenantId = tenantId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }

    override fun getProductClassById(): Single<MultiposResponseSingle<ProductClass>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateProductClass(): Single<MultiposResponseSingle<ProductClass>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteProductClass(): Single<MultiposResponseSingle<ProductClass>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createProductClass(): Single<MultiposResponseSingle<ProductClass>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getData(): Single<SignUp> {
        return service.getData()
    }
}