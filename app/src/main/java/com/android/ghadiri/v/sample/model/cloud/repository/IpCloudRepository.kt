package com.android.ghadiri.v.sample.model.cloud.repository

import com.android.ghadiri.v.sample.model.cloud.CloudApiContract
import javax.inject.Inject

/**
 * @author vahabghadiri
 * @since 8/12/18
 */
class IpCloudRepository @Inject constructor(
    private val api: CloudApiContract
) {

    fun getCurrentIP() = api.getIP()

}