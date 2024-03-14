package com.listshop.bff.services.impl

import com.listshop.bff.data.remote.ApiDeviceInfo
import com.listshop.bff.remote.UserApi
import com.listshop.bff.services.UserService
import com.listshop.bff.services.UserSessionService

class UserServiceImpl internal constructor(
    private val remoteApi: UserApi,
    private val sessionService: UserSessionService
) : UserService {
    override suspend fun authenticateUser() {
        if (sessionService.currentSession().userToken == null) {
            //MM errors! how to throw errors!!
        }
        val postDeviceInfo = buildDeviceInfo()
        remoteApi.authenticateUser(postDeviceInfo)
        //MM nfl - do properties here
    }

    private fun buildDeviceInfo(): ApiDeviceInfo {
        val appInfo = sessionService.currentAppInfo()
        return ApiDeviceInfo(
           appInfo.name,
            appInfo.model,
            appInfo.os,
            appInfo.osVersion,
            appInfo.clientType,
            appInfo.clientVersion,
            appInfo.buildNumber,
            appInfo.deviceId
        )
    }


}