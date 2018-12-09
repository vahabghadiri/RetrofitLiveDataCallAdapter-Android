package com.android.ghadiri.v.sample.liveadapter

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import retrofit2.HttpException

/**
 * @author vahabghadiri
 * @since 9/12/18
 */
class NetworkLiveData<T> : MutableLiveData<NetworkLiveData.NetworkData<T?>>() {

    private lateinit var observer: NetworkData<T?>.() -> Unit

    internal fun onSuccessResponse(body: T?) {
        val result = NetworkData.Success(body)
        postValue(result)
    }

    fun observeForever(f: NetworkData<T?>.() -> Unit) {
        observer = f
        super.observeForever(f)
    }

    internal fun onResponseHttpException(httpException: HttpException) {
        val result = NetworkData.ErrorResponse<T?>(httpException)
        postValue(result)
    }

    internal fun onFailedRequest(t: Throwable) {
        val result = NetworkData.ErrorRequest<T?>(t)
        postValue(result)
    }

    fun onFinish() {
        Handler(Looper.getMainLooper()).post {
            this.removeObserver(observer)
        }
    }

    sealed class NetworkData<T> {
        data class Success<T>(val value: T) : NetworkData<T>()
        data class ErrorResponse<T>(val httpException: HttpException) : NetworkData<T>()
        data class ErrorRequest<T>(val t: Throwable) : NetworkData<T>()

        fun onSuccess(f: (responseBody: T) -> Unit) {
            if (this is Success) {
                f.invoke(value)
            }
        }

        fun onHttpException(f: (code: Int, message: String) -> Unit) {
            if (this is ErrorResponse) {
                f.invoke(httpException.code(), httpException.message())
            }
        }

        fun onConnectionFailed(f: (t: Throwable) -> Unit) {
            if (this is ErrorRequest) {
                f.invoke(t)
            }
        }
    }
}