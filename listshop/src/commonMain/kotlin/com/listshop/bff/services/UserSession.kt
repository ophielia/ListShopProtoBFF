package com.listshop.bff.services

import com.listshop.bff.data.state.ConnectionState
import com.listshop.bff.data.state.UserSessionState

data class UserSession(
    var userName: String?,
    var userToken: String?,
    var sessionState: UserSessionState,
    var connectionState: ConnectionState,
    var appVersion: String,
    var appBuild: String,
    var baseUrl: String
) {
}