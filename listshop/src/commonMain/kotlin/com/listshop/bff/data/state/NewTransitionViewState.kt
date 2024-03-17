package com.listshop.bff.data.state

import com.listshop.bff.data.model.ListShoppingList


sealed class NewTransitionViewState<out T : Any> {
    data object Launching : NewTransitionViewState<Nothing>()
    class ListManagementScreen<ListShoppingList : Any>(val data: ListShoppingList) : NewTransitionViewState<ListShoppingList>()
    class Onboarding<String : Any>( val data: String) : NewTransitionViewState<String>()
}