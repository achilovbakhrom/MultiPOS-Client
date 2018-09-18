package com.jim.multipos.core.managers

import com.jim.multipos.environment.admin.model.SignUp
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {

    @GET("todos/1")
    fun getData(): Single<SignUp>
}