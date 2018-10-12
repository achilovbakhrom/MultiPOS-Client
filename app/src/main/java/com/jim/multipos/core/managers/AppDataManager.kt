package com.jim.multipos.core.managers

import com.jim.multipos.core.MultiposResponseList
import com.jim.multipos.core.MultiposResponseSingle
import com.jim.multipos.environment.admin.model.*
import com.jim.multipos.utils.PrefsManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(var service: NetworkService, var authService: AuthService) : DataManager {

    override fun signIn(username: String, password: String, grant_type: String, client_id: String, client_secret: String): Single<MultiposResponseSingle<SignIn>> {
        return authService.signIn(username, password, grant_type, client_id, client_secret)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }


    override fun signUp(signUp: SignUp): Single<MultiposResponseSingle<SignUp>> {
        return authService.signUp(signUp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())

    }

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