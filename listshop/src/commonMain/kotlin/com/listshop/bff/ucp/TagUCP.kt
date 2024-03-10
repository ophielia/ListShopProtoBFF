package com.listshop.bff.ucp

import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bff.data.model.Tag
import com.listshop.bff.remote.ListShopApi
import com.listshop.bff.repositories.TagRepository

class TagUCP internal constructor(
    private val dataRepo: TagRepository,
    private val listshopApi: ListShopApi,
    private val listShopAnalytics: ListShopAnalytics
) {

    @Throws(Exception::class) suspend fun getTags(forceReload: Boolean): List<Tag> {
        val cachedTags = dataRepo.selectAllTags()
        return if (cachedTags.isNotEmpty() && !forceReload) {
            cachedTags
        } else {
            listshopApi.getAllTags().also {
                listShopAnalytics.tagsFetchedFromNetwork(it.size)
                dataRepo.deleteAll()
                dataRepo.insertTags(it)
            }
        }
    }
}


