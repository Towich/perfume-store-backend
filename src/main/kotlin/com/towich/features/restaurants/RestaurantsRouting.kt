package com.towich.features.restaurants

import com.towich.features.register.RegisterController
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRestaurantsRouting() {
    val restaurantsController = RestaurantsController()

    routing {
        get("/restaurants") {
            restaurantsController.performGetAllRestaurants(call)
        }
    }
}
