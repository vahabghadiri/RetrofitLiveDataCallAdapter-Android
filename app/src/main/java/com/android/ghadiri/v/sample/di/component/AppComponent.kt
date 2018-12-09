package com.android.ghadiri.v.sample.di.component

import com.android.ghadiri.v.sample.di.module.NetworkModule
import com.android.ghadiri.v.sample.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * @author vahab ghadiri
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface AppComponent {
    fun inject(viewModel: MainViewModel)
}