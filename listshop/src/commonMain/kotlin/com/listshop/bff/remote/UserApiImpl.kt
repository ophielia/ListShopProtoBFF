package com.listshop.bff.remote

import com.listshop.bff.data.remote.ApiDeviceInfo
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


}
