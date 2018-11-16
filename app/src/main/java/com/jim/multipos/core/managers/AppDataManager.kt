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

    override fun signIn(singInRequest: SignInRequest): Single<MultiposResponseSingle<SignIn>> {
        return authService.signIn(singInRequest)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }


    override fun signUp(signUp: SignUp): Single<MultiposResponseSingle<SignUp>> {
        return authService.signUp(signUp)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun createCompany(companyDTO: CompanyDTO): Single<MultiposResponseSingle<CompanyDTO>> {
        return service.createCompany(companyDTO)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    override fun getProductClassList(page: Int, pageSize: Int): Single<MultiposResponseList<ProductClass>> {
        return service
                .getProductClassList(page = page, pageSize = pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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

    override fun getCompanies(): Single<MultiposResponseList<CompanyDTO>> {
        return service.getCompanies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

}