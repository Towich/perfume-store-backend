package com.towich.features.restaurants

import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRestaurantsRouting() {
    val restaurantsController = RestaurantsController()

    routing {
        get("/restaurants", {
            tags = listOf("Restaurants")
            description = "Get all restaurants"
            response {
                HttpStatusCode.OK to {
                    description = "Successful"
                    body<RestaurantRespond>()
                }
            }
        }) {
            restaurantsController.performGetAllRestaurants(call)
        }
    }
}
