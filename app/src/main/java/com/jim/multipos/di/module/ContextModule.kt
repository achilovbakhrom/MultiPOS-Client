package com.jim.multipos.di.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class ContextModule {
    @Binds
    @Singleton
    abstract fun provideContext(app: Application): Context
}