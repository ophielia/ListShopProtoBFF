package com.listshop.bff.services

import com.listshop.bff.repositories.ListShopRepository

class UserSessionService internal constructor(
    private val database: ListShopRepository,
) {
    var _userSession: UserSession? = null

    // public getter
    var userSession: UserSession? = null
        get() = getUserSession()

    fun getUserSession(): UserSession? {
        return _userSession;
    }
}