package com.jim.multipos.core.managers

import com.jim.multipos.core.MultiposResponseList
import com.jim.multipos.core.MultiposResponseSingle
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.environment.admin.model.SignUp
import io.reactivex.Single
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface NetworkService {

    @GET("todos/1")
    fun getData(): Single<SignUp>


    // Product class api
    @GET("/api/v1/product-class-list")
    fun getProductClassList(page: Int = 0): Single<MultiposResponseList<ProductClass>>

    @GET("/api/v1/product-class/id")
    fun getProductClassById(): Single<MultiposResponseSingle<ProductClass>>

    @PUT("/api/v1/product-class/update/id")
    fun updateProductClass(): Single<MultiposResponseSingle<ProductClass>>

    @DELETE("/api/v1/product-class/id")
    fun deleteProductClass(): Single<MultiposResponseSingle<ProductClass>>

    @POST("/api/v1/product-class/create")
    fun createProductClass(): Single<MultiposResponseSingle<ProductClass>>






}