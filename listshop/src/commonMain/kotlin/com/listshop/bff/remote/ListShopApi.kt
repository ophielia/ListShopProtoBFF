package com.listshop.bff.remote

import com.listshop.bff.data.model.Tag

internal interface ListShopApi {
    suspend fun getAllTags(): List<Tag>
}
