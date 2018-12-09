package com.android.ghadiri.v.sample.liveadapter

import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @author vahabghadiri
 * @since 8/12/18
 */
class LiveDataCallAdapterFactory private constructor() : CallAdapter.Factory() {
    companion object {
        operator fun invoke() = LiveDataCallAdapterFactory()
    }

    override fun get(
        returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit
    ): CallAdapter<*, *>? {

        if (getRawType(returnType) != NetworkLiveData::class.java) {
            return null
        }

        if (returnType !is ParameterizedType) {
            throw IllegalStateException(
                "must be parameterized"
            )
        }
        val responseType = getParameterUpperBound(0, returnType)
        return BodyCallAdapter<Any>(responseType)
    }


    private class BodyCallAdapter<T>(
        private val responseType: Type
    ) : CallAdapter<T, NetworkLiveData<T>> {

        override fun responseType() = responseType

        override fun adapt(call: Call<T>): NetworkLiveData<T> {
            val liveData = NetworkLiveData<T>()

            call.enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    liveData.onFailedRequest(t)
                    liveData.onFinish()
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        liveData.onSuccessResponse(response.body())
                    } else {
                        liveData.onResponseHttpException(HttpException(response))
                    }
                    liveData.onFinish()
                }
            })
            return liveData
        }
    }
}