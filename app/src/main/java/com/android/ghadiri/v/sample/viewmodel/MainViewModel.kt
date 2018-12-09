package com.android.ghadiri.v.sample.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.ghadiri.v.sample.model.cloud.repository.IpCloudRepository
import com.android.ghadiri.v.sample.utils.log
import javax.inject.Inject

class MainViewModel : BaseViewModel() {

    @Inject
    lateinit var ipCloudRepository: IpCloudRepository
    var ipLiveData = MutableLiveData<String>()

    init {
        bind().inject(this)
    }

    fun getMyDeviceIp() {

        ipCloudRepository.getCurrentIP().observeForever {

            onSuccess {
                it.log()
                ipLiveData.value = it
            }
            onHttpException { code, message ->
                code.log()
                message.log()
            }
            onConnectionFailed { it.message.log() }

            "finished!".log()
        }
    }

}
