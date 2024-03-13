package com.listshop.bff.services

import com.listshop.bff.data.remote.ApiDeviceInfo
import com.listshop.bff.remote.UserApi

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
        val appInfo = sessionService.appInfo
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