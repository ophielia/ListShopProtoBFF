package com.listshop.bff.services

data class UserSession(
    val userName: String?,
    val userToken: String?
) {
}