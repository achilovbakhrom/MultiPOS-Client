package com.jim.multipos.core.managers

import com.jim.multipos.core.MultiposResponseSingle
import com.jim.multipos.environment.admin.model.SignIn
import com.jim.multipos.environment.admin.model.SignUp
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/sign-up")
    fun signUp(@Body signUp: SignUp): Single<MultiposResponseSingle<SignUp>>

    @POST("/api/v1/sign-in")
    fun signIn(@Field("username") username: String,
               @Field("password") password: String,
               @Field("grant_type") grant_type: String,
               @Field("client_id") client_id: String,
               @Field("client_secret") client_secret: String): Single<MultiposResponseSingle<SignIn>>

}