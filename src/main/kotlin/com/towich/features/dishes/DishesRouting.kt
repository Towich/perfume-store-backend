package com.towich.features.dishes

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureDishesRouting() {
    val dishesController = DishesController()

    routing {
        get("/dishes"){
            dishesController.performGetAllDishes(call)
        }
        get("/dishes/{category}") {
            dishesController.performGetDishesByCategory(call)
        }
    }
}
