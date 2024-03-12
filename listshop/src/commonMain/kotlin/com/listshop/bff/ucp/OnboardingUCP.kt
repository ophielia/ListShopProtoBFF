package com.listshop.bff.ucp

import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bff.data.bff.BFFResult
import com.listshop.bff.data.model.ListShoppingList
import com.listshop.bff.data.model.ShoppingList
import com.listshop.bff.remote.ShoppingListApi
import com.listshop.bff.repositories.SessionInfoRepository

class OnboardingUCP internal constructor(
    private val sessionRepo: SessionInfoRepository,
    private val shoppingListApi: ShoppingListApi,
    private val listShopAnalytics: ListShopAnalytics
) {

    @Throws(Exception::class)
    suspend fun systemGetLaunchScreen(): BFFResult<ListShoppingList> {
        val list = listOf(ShoppingList.create("1", "name"))
        val listOfLists = ListShoppingList(list)
        return BFFResult(listOfLists,null)
    }

}


