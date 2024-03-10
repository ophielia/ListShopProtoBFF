package com.listshop.bff.ucp

import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bff.data.bff.BFFResult
import com.listshop.bff.data.model.ShoppingList
import com.listshop.bff.repositories.ListShopDatabase
import com.listshop.bff.remote.ListShopApi
import com.listshop.bff.repositories.TagRepository

class OnboardingUCP internal constructor(
    private val dbHelper: TagRepository,
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
    @Throws(Exception::class) suspend fun systemGetLaunchScreen(): BFFResult<ShoppingList> {
        return  BFFResult(ShoppingList.create("1","name"),null);
    }

}


