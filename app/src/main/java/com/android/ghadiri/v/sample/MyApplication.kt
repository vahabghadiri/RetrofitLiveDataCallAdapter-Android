package com.android.ghadiri.v.sample

import android.app.Application
import com.android.ghadiri.v.sample.di.component.AppComponent
import com.android.ghadiri.v.sample.di.component.DaggerAppComponent

/**
 * @author vahabghadiri
 * @since 8/12/18
 */
class MyApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        setupComponent()
    }

    private fun setupComponent() {
        appComponent = DaggerAppComponent
            .builder()
            .build()
    }
}