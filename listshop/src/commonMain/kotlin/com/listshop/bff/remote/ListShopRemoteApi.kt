package com.listshop.bff.remote

import io.ktor.client.HttpClient

internal interface ListShopRemoteApi {
    fun client( token: String?): HttpClient
    fun token(): String?
    fun buildPath(path: String): String
}
