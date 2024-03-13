package com.listshop.bff.remote

import com.listshop.bff.data.remote.ApiDeviceInfo

internal interface UserApi {

    suspend fun authenticateUser(postDeviceInfo: ApiDeviceInfo)

}
