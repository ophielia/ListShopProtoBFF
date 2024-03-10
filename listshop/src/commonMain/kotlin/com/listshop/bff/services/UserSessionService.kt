package com.listshop.bff.services

import com.listshop.bff.db.UserInfoEntity
import com.listshop.bff.repositories.SessionInfoRepository

class UserSessionService internal constructor(
    private val sessionRepo: SessionInfoRepository
) {
    private var _userSession: UserSession? = null

    fun currentSession(): UserSession {
        if (_userSession != null) {
            return _userSession!!
        }
        initializeUserSession()
        return _userSession!!


    }

    private fun getOrCreateUserInfo(): UserInfoEntity {
        var userInfo = sessionRepo.getUserInfo()
        if (userInfo != null) {
            return userInfo
        }
        userInfo = sessionRepo.createUserInfo()
        return userInfo!!
    }

    private fun determineUserSessionState(userInfo: UserInfoEntity): UserSessionState {
        if (userInfo.userName != null && userInfo.userToken != null) {
            return UserSessionState.User
        }
        if (userInfo.userName != null) {
            return UserSessionState.UserLoggedOut
        }
        //MM nfl (note for later) skipping local list right now
        return UserSessionState.Anon
    }

    private fun initializeUserSession() {
        val userInfo = getOrCreateUserInfo()
        val sessionState = determineUserSessionState( userInfo)
        _userSession = UserSession(userInfo.userName,
            userInfo.userToken,
            sessionState)
    }


    /**
     *

    public func updateUserInfo() {
    saveContext()
    }

    public func setUserCreated() {
    userInfo = getOrCreateUserInfo()
    userInfo?.userCreated = Date().iso8601
    coreDataApi.updateUserInfo()
    refreshUserSession()
    }

     *
    private func refreshUserSession() {
    userInfo = getOrCreateUserInfo()
    listInfo = getOrCreateListInfo()
    let sessionState = determineUserSessionState(for: userInfo!, with: listInfo!)
    let connectionState = privateUserSession?.connectionState ?? ConnectionState.online
    let teaserCounts = privateUserSession?.teaserCounts ?? [:]
    let sessionListMemory = getOrCreateSessionListMemory()
    let sessionDishMemory = getOrCreateSessionDishMemory()
    privateUserSession = UserSession(userInfo: userInfo!,
    listInfo: listInfo!,
    sessionStateList: sessionListMemory,
    sessionStateDish: sessionDishMemory,
    sessionState: sessionState,
    connectionState: connectionState,
    teaserCounts: teaserCounts,
    version: versionNumber,
    buildNumber: buildNumber)
    }
     */


}