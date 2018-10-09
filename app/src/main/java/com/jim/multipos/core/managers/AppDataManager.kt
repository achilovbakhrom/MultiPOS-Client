package com.jim.multipos.core.managers

import com.jim.multipos.core.MultiposResponseList
import com.jim.multipos.core.MultiposResponseSingle
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.environment.admin.model.SignUp
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager : DataManager {



    override fun getProductClassList(page: Int): Single<MultiposResponseList<ProductClass>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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


    var service: NetworkService

    @Inject
    constructor(service: NetworkService) {
        this.service = service
    }

    override fun getData(): Single<SignUp> {
        return service.getData()
    }
}