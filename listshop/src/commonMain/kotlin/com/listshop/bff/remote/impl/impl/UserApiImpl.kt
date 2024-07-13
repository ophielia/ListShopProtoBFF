package com.listshop.bff.remote.impl

import com.listshop.bff.data.remote.ApiDeviceInfo
import com.listshop.bff.data.remote.ApiWrappedUser
import com.listshop.bff.data.remote.PostUserLogin
import com.listshop.bff.remote.ListShopRemoteApi
import com.listshop.bff.remote.UserApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

internal class UserApiImpl(
    val remoteApi: ListShopRemoteApi
) : UserApi {



    override suspend fun authenticateUser(postDeviceInfo: ApiDeviceInfo)  {

        //val response: HttpResponse = remoteApi.client(remoteApi.token())
        remoteApi.client(remoteApi.token())
            .post("/auth/authenticate") {
                contentType(ContentType.Application.Json)
                setBody(postDeviceInfo)
        }

        //MM nfl check response
    }
    override suspend fun signInUser(postLoginUser: PostUserLogin): String {

        val urlString = remoteApi.buildPath("/auth")
        val response: ApiWrappedUser =remoteApi.client(remoteApi.token())
            .post(urlString) {
                contentType(ContentType.Application.Json)
                setBody(postLoginUser)
        }.body()

        //MM nfl handle signin failure
        return response.user.token ?: "token"
    }

    override suspend fun logoutUser() {

        val urlString = remoteApi.buildPath("/auth/logout")
        remoteApi.client(remoteApi.token()).get(urlString)

        //MM nfl handle  failure
    }


}
