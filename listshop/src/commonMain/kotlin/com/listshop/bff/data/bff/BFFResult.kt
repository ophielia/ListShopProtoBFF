package com.listshop.bff.data.bff

class BFFResult<T> internal constructor(
    private val value: T,
    private val error: BFFError?
)