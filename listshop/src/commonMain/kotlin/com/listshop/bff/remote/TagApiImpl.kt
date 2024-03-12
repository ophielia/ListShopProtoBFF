package com.listshop.bff.remote

import com.listshop.bff.data.model.Tag
import com.listshop.bff.data.remote.ApiTagLookupEmbedded
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class TagApiImpl(
    val remoteApi: ListShopRemoteApi
) : TagApi {

    override suspend fun getAllTags(): List<Tag> {
        val token = remoteApi.token()
        val result: ApiTagLookupEmbedded =
            remoteApi.client(token).get("/tag/user").body()

        return result.embeddedList.tagLookupResourceList
            .map { et -> et.embeddedTag }
            .map { at -> Tag.create(at) }
    }


}
