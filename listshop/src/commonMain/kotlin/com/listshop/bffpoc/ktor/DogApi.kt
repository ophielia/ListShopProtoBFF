package com.listshop.bffpoc.ktor

import com.listshop.bffpoc.response.BreedResult

internal interface DogApi {
    suspend fun getJsonFromApi(): BreedResult
}
