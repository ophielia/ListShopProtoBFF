package com.listshop.bffpoc.repository

import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bffpoc.ListShopRepository
import com.listshop.bffpoc.data.model.Tag
import com.listshop.bffpoc.ktor.ListShopApi
import kotlinx.coroutines.flow.*

class TagUCP internal constructor(
    private val dbHelper: ListShopRepository,
    private val listshopApi: ListShopApi,
    private val listShopAnalytics: ListShopAnalytics
) {

    @Throws(Exception::class) suspend fun getTags(forceReload: Boolean): List<Tag> {
        val cachedTags = dbHelper.selectAllTags()
        return if (cachedTags.isNotEmpty() && !forceReload) {
            cachedTags
        } else {
            listshopApi.getAllTags().also {
                listShopAnalytics.tagsFetchedFromNetwork(it.size)
                dbHelper.deleteAll()
                dbHelper.insertTags(it)
            }
        }
    }
}


