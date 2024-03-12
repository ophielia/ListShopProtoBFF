package com.listshop.bff.remote

import com.listshop.bff.data.model.ShoppingList
import com.listshop.bff.data.remote.ApiShoppingListEmbedded
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class ShoppingListApiImpl(
    val remoteApi: ListShopRemoteApi
) : ShoppingListApi {

    override suspend fun getAllShoppingLists(): List<ShoppingList> {
        val token = remoteApi.token()
        val result: ApiShoppingListEmbedded =
            remoteApi.client(token).get("/shoppinglist").body()

        return result.embeddedList.shoppingListResourceList
            .map { el -> el.embeddedList}
            .map { el -> ShoppingList.create(apiValue = el) }
    }
}
