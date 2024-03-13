package com.listshop.bff.data.bff

public class BFFResult<T> internal constructor(
    val value: T?
) {

    var error: BFFError? = null
    public val isSuccess: Boolean get() = error == null

    public val isFailure: Boolean get() = error != null

    public companion object {
        public  fun <T> success(value: T): BFFResult<T> {
            return BFFResult(value)
        }

    }

}