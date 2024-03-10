package com.listshop.bff.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiShoppingList(
    @SerialName("list_id")
    val externalId: String?,
    @SerialName("created")
    val created: String?,
    @SerialName("updated")
    val updated: String?,
    @SerialName("item_count")
    val itemCount: Int?,
    @SerialName("layout_id")
    val layoutId: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("is_starter_list")
    val isStarter: Boolean?
    //let legend: [ApiLegend]?
    //case legend = "legend

    /*

        var externalId: Int?
    let categories: [ShoppingListCategory]?
    let created: String?
    let updated: String?
    let layoutId: String?
    let itemCount: Int?
    var name: String?
    let isStarterList: Bool?
    var legend: ShoppingListLegend?
    let loading: Bool
    var lastLocalChange: String?
    var lastSynced: String?
     */
)
