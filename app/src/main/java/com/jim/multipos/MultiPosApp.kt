package com.jim.multipos

import android.app.Activity
import android.app.Application
import com.jim.multipos.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import uk.co.chrisjenx.calligraphy.CalligraphyConfig



class MultiPosApp : Application(), HasActivityInjector{

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

//    @Inject
//    var mCalligraphyConfig: CalligraphyConfig? = null

    override fun activityInjector() = activityInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)

//        CalligraphyConfig.initDefault(mCalligraphyConfig)

    }
}