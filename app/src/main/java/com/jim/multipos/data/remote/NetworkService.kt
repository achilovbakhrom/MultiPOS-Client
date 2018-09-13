package com.jim.multipos.data.remote

import com.jim.multipos.data.model.adminsignup.AdminSignUpModel
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface NetworkService {

    @GET("todos/1")
    fun getData(): Single<AdminSignUpModel>
}