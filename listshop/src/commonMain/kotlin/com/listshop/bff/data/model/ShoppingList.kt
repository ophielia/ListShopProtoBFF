package com.listshop.bff.data.model

import com.listshop.bff.data.remote.ApiShoppingList

data class ShoppingList(
    var externalId: String?,
    // categories let categories: [ShoppingListCategory]?
    var created: String?,
    var updated: String?,
    var layoutId: String?,
    var itemCount: Int?,
    val name: String?,
    val isStarterList: Boolean?,
    // var legend: ShoppingListLegend?
    val loading: Boolean,
    val lastLocalChange: String?,
    val lastSynced: String?
) {
    companion object Factory {
        fun create(apiValue: ApiShoppingList): ShoppingList {
            return ShoppingList(
                apiValue.externalId,
                apiValue.created,
                apiValue.updated,
                apiValue.layoutId,
                apiValue.itemCount,
                apiValue.name,
                apiValue.isStarter,
                false,  // is loading
                null,
                null
            )
        }
    }

}

