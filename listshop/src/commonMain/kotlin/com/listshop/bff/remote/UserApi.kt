package com.listshop.bff.remote

import com.listshop.bff.data.model.Tag

internal interface UserApi {

    suspend fun getAllTags(): List<Tag>
}
