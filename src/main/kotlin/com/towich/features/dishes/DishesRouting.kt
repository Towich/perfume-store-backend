package com.towich.features.dishes

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureGetDishesRouting() {
    val dishesController = DishesController()

    routing {
        get("/dishes") {
            dishesController.performGetDishes(call)
        }
    }
}
