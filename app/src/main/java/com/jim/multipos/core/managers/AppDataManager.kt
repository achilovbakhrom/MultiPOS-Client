package com.jim.multipos.core.managers

import com.jim.multipos.environment.admin.model.SignUp
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager : DataManager {

    var service: NetworkService

    @Inject
    constructor(service: NetworkService) {
        this.service = service
    }

    override fun getData(): Single<SignUp> {
        return service.getData()
    }
}