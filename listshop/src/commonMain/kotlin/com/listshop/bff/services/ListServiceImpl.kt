package com.listshop.bff.services

import com.listshop.bff.data.model.ShoppingList
import com.listshop.bff.remote.ShoppingListApi

class ListServiceImpl internal constructor(
    private val remoteApi: ShoppingListApi,
    private val sessionService: UserSessionService
) : ListService {
    override suspend fun retrieveListOfLists(): List<ShoppingList> {
        return remoteApi.getAllShoppingLists()
    }


}