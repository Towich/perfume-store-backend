package com.towich.features.restaurants

import io.ktor.server.application.*

interface IRestaurantsController {
    suspend fun performGetAllRestaurants(call: ApplicationCall)
}