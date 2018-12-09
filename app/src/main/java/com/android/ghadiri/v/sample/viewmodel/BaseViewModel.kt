package com.android.ghadiri.v.sample.viewmodel

import androidx.lifecycle.ViewModel
import com.android.ghadiri.v.sample.MyApplication

/**
 * @author vahabghadiri
 * @since 8/12/18
 */
open class BaseViewModel : ViewModel() {

    fun bind() = MyApplication.appComponent

}