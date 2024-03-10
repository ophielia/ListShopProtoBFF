package com.listshop.bff.data.bff

data class BFFError(
    var id: String,
    var type: BFFErrorType,
    var title: String,
    var message: String
)

