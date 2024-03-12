package com.listshop.bff.remote

import com.listshop.bff.data.model.Tag
import com.listshop.bff.data.remote.ApiTagLookupEmbedded
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class UserApiImpl(
    val remoteApi: ListShopRemoteApi
) : UserApi {

    override suspend fun getAllTags(): List<Tag> {
        val result: ApiTagLookupEmbedded =
            remoteApi.client(null).get("https://nastyvarmits.fr/api/tag/user").body()

        return result.embeddedList.tagLookupResourceList
            .map { et -> et.embeddedTag }
            .map { at -> Tag.create(at) }
    }

    /**
     * suspend fun fetchTopHeadlines(newzFeedUrl: String = NEWZ_FEED_URL, country: String = NEWZ_FEED_COUNTRY_US): NewzHeadlinesContent = client.get("$newzFeedUrl?country=$country$NEWZ_FEED_APPEND_API_KEY"){
     *     header(
     *         MY_NEWZ_FEED_SERVICE_TYPE,
     *         MY_NEWZ_FEED_SERVICE_HEADLINES
     *     )
     * }.body()
     */
}
