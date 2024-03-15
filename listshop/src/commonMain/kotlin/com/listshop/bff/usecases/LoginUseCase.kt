package com.listshop.bff.usecases

import com.listshop.bff.data.bff.BFFResult
import com.listshop.bff.data.model.ListShoppingList
import com.listshop.bff.data.state.TransitionViewState
import com.listshop.bff.services.ListService
import com.listshop.bff.services.UserService
import com.listshop.bff.services.UserSessionService

class LoginUseCase(
    private val userName: String,
    private val password: String,
    private val userService: UserService,
    private val sessionService: UserSessionService,
    private val listService: ListService
) {

    suspend fun process(): BFFResult<TransitionViewState> {
         //MM nfl - skipping checking api compatibility for now
        //        - skipping first seen (will need userLastSeen)
       userService.signInUser(userName, password)
        val listOfLists = listService.retrieveListOfLists()
        val wrappedLists = ListShoppingList(listOfLists)
        return BFFResult.success(TransitionViewState.ListManagementScreen(wrappedLists))

    }

}