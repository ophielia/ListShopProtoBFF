package com.listshop.bff.services

import co.touchlab.kmmbridgekickstart.AppInfo

interface UserSessionService {

    fun currentSession(): UserSession

    fun currentAppInfo(): AppInfo

    fun setUserToken(token: String?)

    fun setUserName(name: String)

    fun setUserLastSeenToNow()

    fun setUserLastSignedInToNow()


}