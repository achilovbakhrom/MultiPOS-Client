package com.jim.multipos.di.component

import android.app.Application
import android.content.Context
import com.jim.multipos.MultiPosApp
import com.jim.multipos.di.builder.ActivityBuilder
import com.jim.multipos.di.module.AppModule
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Suppress("unused")
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class
    ])
interface AppComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app: Application):Builder

        fun build():AppComponent

    }

    fun inject(app:MultiPosApp)

}