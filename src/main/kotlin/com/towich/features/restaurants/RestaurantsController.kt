package com.towich.features.restaurants

import com.towich.database.restaurants.Restaurants
import io.ktor.server.application.*
import io.ktor.server.response.*

class RestaurantsController : IRestaurantsController {
    override suspend fun performGetAllRestaurants(call: ApplicationCall) {
        val listOfRestaurants: List<RestaurantRespond> =
            Restaurants.getAllRestaurants().map { it.convertToGetAllRestaurantsRespond() }

        call.respond(listOfRestaurants)
    }
}