package com.listshop.bffpoc.data.model

import com.listshop.bffpoc.data.api.ApiTagLookup
import com.listshop.bffpoc.db.TagLookupEntity

data class Tag(
    val externalId: String?,
    val name: String?,
    val parentId: String?,
    val tagType: String?,
) {
    companion object Factory {
        fun create(apiValue: ApiTagLookup): Tag {
            return Tag(
                apiValue.externalId,
                apiValue.name,
                apiValue.parentId,
                apiValue.tagType
            )
        }

        fun create(dbValue: TagLookupEntity): Tag {
            return Tag(
                dbValue.externalId,
                dbValue.name,
                dbValue.parentId,
                dbValue.tagType
            )
        }
    }

}

