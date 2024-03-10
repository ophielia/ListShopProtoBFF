package com.listshop.bff.ucp

import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bff.repositories.ListShopRepository
import com.listshop.bff.data.model.Tag
import com.listshop.bff.remote.ListShopApi

class OnboardingUCP internal constructor(
    private val dbHelper: ListShopRepository,
    private val listshopApi: ListShopApi,
    private val listShopAnalytics: ListShopAnalytics
) {

/*
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
 */
    @Throws(Exception::class) suspend fun systemGetLaunchScreen(): List<Tag> {
        return  emptyList();
    }

}


