package com.jim.multipos.data

import com.jim.multipos.data.model.adminsignup.AdminSignUpModel
import com.jim.multipos.data.remote.NetworkService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager : DataManager{

    var service: NetworkService

    @Inject
    constructor(service: NetworkService) {
        this.service = service
    }

    override fun getData(): Single<AdminSignUpModel> {
        return service.getData()
    }
}