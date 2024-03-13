package com.listshop.bff.ucp

import co.touchlab.kmmbridgekickstart.ListShopAnalytics
import com.listshop.bff.data.bff.BFFResult
import com.listshop.bff.data.state.TransitionViewState
import com.listshop.bff.services.ListService
import com.listshop.bff.services.UserSessionService
import com.listshop.bff.usecases.SystemGetLaunchScreenUseCase

class OnboardingUCP internal constructor(
    private val sessionService: UserSessionService,
    private val listService: ListService,
    private val listShopAnalytics: ListShopAnalytics
) {

    @Throws(Exception::class)
    suspend fun systemGetLaunchScreen(): BFFResult<TransitionViewState> {
        val useCase = SystemGetLaunchScreenUseCase(sessionService,, listService)
        return useCase.process()
    }

}


