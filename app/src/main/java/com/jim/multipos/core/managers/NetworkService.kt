package com.jim.multipos.core.managers

import com.jim.multipos.core.MultiposResponseList
import com.jim.multipos.core.MultiposResponseSingle
import com.jim.multipos.environment.admin.model.ProductClass
import com.jim.multipos.environment.admin.model.SignUp
import io.reactivex.Single
import retrofit2.http.*

interface NetworkService {

    @GET("todos/1")
    fun getData(): Single<SignUp>


    // Product class api
    @GET("/api/v1/product-class-list")
    fun getProductClassList(@Query("page") page: Int = 0,
                            @Query("page_size") pageSize: Int = 20,
                            @Header("Authorization") token: String,
                            @Header("X-TENANT-ID") tenantId: String): Single<MultiposResponseList<ProductClass>>

    @GET("/api/v1/product-class/id")
    fun getProductClassById(): Single<MultiposResponseSingle<ProductClass>>

    @PUT("/api/v1/product-class/update/id")
    fun updateProductClass(): Single<MultiposResponseSingle<ProductClass>>

    @DELETE("/api/v1/product-class/id")
    fun deleteProductClass(): Single<MultiposResponseSingle<ProductClass>>

    @POST("/api/v1/product-class/create")
    fun createProductClass(): Single<MultiposResponseSingle<ProductClass>>






}