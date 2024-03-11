package com.listshop.bff.services

import com.listshop.bff.data.model.UserInfo
import com.listshop.bff.data.state.ConnectionState
import com.listshop.bff.data.state.UserSessionState
import com.listshop.bff.db.UserInfoEntity
import com.listshop.bff.repositories.SessionInfoRepository
import kotlinx.datetime.Clock

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

     fun setUserToken(token: String) {
         var userInfo = getUserInfo()
         userInfo.userToken = token
         updateUserInfo(userInfo)
         refreshUserSession()
    }

    fun setUserName(name: String) {
        var userInfo = getUserInfo()
        userInfo.userName = name
        updateUserInfo(userInfo)
        refreshUserSession()
    }

    fun setUserLastSeenToNow() {
        var userInfo = getUserInfo()
        val now = Clock.System.now()
        userInfo.userLastSeen = now.toString()
        updateUserInfo(userInfo)
        refreshUserSession()
    }

    fun setuserLastSignedInToNow() {
        var userInfo = getUserInfo()
        val now = Clock.System.now()
        userInfo.userLastSignedIn = now.toString()
        updateUserInfo(userInfo)
        refreshUserSession()
    }
    private fun updateUserInfo(userInfo: UserInfo) {
        sessionRepo.updateUserInfo(userInfo)
    }

    private fun getUserInfo(): UserInfo {
        val userInfoEntity = getOrCreateUserInfoEntity()
        return UserInfo.create(userInfoEntity)
    }


    private fun getOrCreateUserInfoEntity(): UserInfoEntity {
        var userInfo = sessionRepo.getUserInfo()
        if (userInfo != null) {
            return userInfo
        }
        userInfo = sessionRepo.createUserInfo()
        return userInfo!!
    }

    private fun refreshUserSession() {
        var userInfo = getOrCreateUserInfoEntity()
        //MM nfl - list info here
        val sessionState = determineUserSessionState(userInfo)
        val connectionState = _userSession?.connectionState ?: ConnectionState.Unknown
        _userSession = UserSession(userInfo.userName,
                userInfo.userToken,
                sessionState,
            connectionState
            )

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
        val userInfo = getOrCreateUserInfoEntity()
        val sessionState = determineUserSessionState( userInfo)
        _userSession = UserSession(userInfo.userName,
            userInfo.userToken,
            sessionState,
            ConnectionState.Unknown)
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

    public enum ConnectionState {
    case online
    case offline
    case unknown
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