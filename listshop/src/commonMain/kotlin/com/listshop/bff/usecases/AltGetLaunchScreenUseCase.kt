package com.listshop.bff.usecases

import com.listshop.bff.data.bff.BFFResult
import com.listshop.bff.data.state.ConnectionStatus
import com.listshop.bff.data.state.NewTransitionViewState
import com.listshop.bff.services.ListService
import com.listshop.bff.services.UserService
import com.listshop.bff.services.UserSessionService

class AltGetLaunchScreenUseCase(
    private val connectionStatus: ConnectionStatus,
    private val sessionService: UserSessionService,
    private val userService: UserService,
    private val listService: ListService
) {

    /*suspend fun process(): Result<TransitionViewState> {
         //MM nfl - skipping checking api compatibility for now
        //        - skipping first seen (will need userLastSeen)
        val session = sessionService.currentSession()

        return when (session.sessionState) {
            UserSessionState.Anon, UserSessionState.UserLoggedOut, UserSessionState.AnonNoList -> {
                val goal = TransitionViewState.Onboarding(OnboardingViewState.Choose)
                Result.success(goal)
            }

            UserSessionState.User -> goToListOfLists()

        }
    }

    private suspend fun goToListOfLists() : Result<TransitionViewState> {
        // authenticate user
        userService.authenticateUser()
        val listOfLists = listService.retrieveListOfLists()
        val wrappedLists = ListShoppingList(listOfLists)
        return Result.success(TransitionViewState.ListManagementScreen(wrappedLists))
    }
*/

    suspend fun process(): BFFResult<NewTransitionViewState<Any>> {
        val goal = NewTransitionViewState.Onboarding("beep")
        return BFFResult.success(goal)

    }

}