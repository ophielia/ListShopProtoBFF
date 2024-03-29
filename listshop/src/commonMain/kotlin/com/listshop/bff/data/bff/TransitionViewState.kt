package com.listshop.bff.data.bff

import com.listshop.bff.data.model.ShoppingList

sealed class TransitionViewState {

    object Launching : TransitionViewState()
    data class ListManagementScreen(val shoppingLists: List<ShoppingList>) : TransitionViewState()
}