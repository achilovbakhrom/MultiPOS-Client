package com.jim.multipos.di.module

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jim.multipos.core.managers.AppDataManager
import com.jim.multipos.core.managers.DataManager
import com.jim.multipos.core.managers.NetworkService
import com.jim.multipos.utils.BASE_URL
import com.jim.multipos.utils.PrefsManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule{

    @Provides
    @Named("simple")
    @Singleton
    fun provideSimpleRetrofit():Retrofit{
        return Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Named("token")
    @Singleton
    fun provideOkHTTPClient(prefManager: PrefsManager) : OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().addHeader(
                    "Authorization",
                    "Bearer ${prefManager.getValue("access_token", "")}"
            ).addHeader("X-TENANT-ID", prefManager.getValue("tenant_id", ""))
                    .build()
            chain.proceed(request)
        }
        return client.build()
    }

    @Provides
    @Named("token")
    @Singleton
    fun provideTokenRetrofit(client: OkHttpClient) :Retrofit{
        return Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }


}