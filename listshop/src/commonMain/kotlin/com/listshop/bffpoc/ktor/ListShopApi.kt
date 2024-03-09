package com.listshop.bffpoc.ktor

import com.listshop.bffpoc.data.model.Tag

internal interface ListShopApi {
    suspend fun getAllTags(): List<Tag>
}
