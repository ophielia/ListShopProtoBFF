package com.listshop.bff.services

interface UserService {

    suspend fun authenticateUser()
    suspend fun signInUser(userName: String, password: String)

}