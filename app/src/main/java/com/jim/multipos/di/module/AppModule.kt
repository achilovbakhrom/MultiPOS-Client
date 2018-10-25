package com.jim.multipos.di.module


import android.content.Context
import android.content.res.Resources
import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.jim.multipos.core.managers.AppDataManager
import com.jim.multipos.core.managers.AuthService
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


@Module(includes = [ContextModule::class, ViewModelModule::class])
class AppModule{

    @Provides
    @Named("simple")
    @Singleton
    fun provideSimpleRetrofit():Retrofit{
        print("google1")
        Log.d("sss", "test1")
        return Retrofit.Builder()
                .client(OkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideOkHTTPClient(prefManager: PrefsManager) : OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder().addHeader(
                    "Authorization",
                    "Bearer ${prefManager.getValue("accessToken", "")}"
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
    fun provideNetworkService(@Named("token") retrofit: Retrofit): NetworkService {
        return retrofit.create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(@Named("simple") retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources = context.resources

}