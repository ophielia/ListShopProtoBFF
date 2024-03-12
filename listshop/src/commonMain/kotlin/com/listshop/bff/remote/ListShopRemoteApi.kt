package com.listshop.bff.remote

import com.listshop.bff.services.UserSessionService
import io.ktor.client.HttpClient

internal interface ListShopRemoteApi {
    fun client( token: String?): HttpClient
    fun sessionService(): UserSessionService
    fun token(): String?
}
