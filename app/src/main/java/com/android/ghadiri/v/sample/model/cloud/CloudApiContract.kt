package com.android.ghadiri.v.sample.model.cloud

import com.android.ghadiri.v.sample.liveadapter.NetworkLiveData
import retrofit2.http.GET

/**
 * @author vahabghadiri
 * @since 8/12/18
 */
interface CloudApiContract {

    @GET("/")
    fun getIP(): NetworkLiveData<String>
}