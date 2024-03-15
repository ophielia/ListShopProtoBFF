package com.listshop.bff.usecases

import com.listshop.bff.data.bff.BFFResult
import com.listshop.bff.data.state.OnboardingViewState
import com.listshop.bff.data.state.TransitionViewState
import com.listshop.bff.services.UserSessionService

class NavigateToDashboardUseCase(
    private val sessionService: UserSessionService,
) {
    suspend fun process(): BFFResult<TransitionViewState> {
        val goal = TransitionViewState.Onboarding(OnboardingViewState.Choose)
        sessionService.setUserLastSeenToNow()

        return BFFResult.success(goal)
    }

}