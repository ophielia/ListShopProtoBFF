package com.listshop.bff.services

interface UserService {

    suspend fun authenticateUser()
}