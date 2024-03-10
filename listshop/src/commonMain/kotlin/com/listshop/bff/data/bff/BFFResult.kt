package com.listshop.bff.data.bff

class BFFResult<T,BFFError> internal constructor(
    private val value: T,
    private val error: BFFError
)