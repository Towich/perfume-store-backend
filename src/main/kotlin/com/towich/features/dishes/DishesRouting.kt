package com.towich.features.dishes

import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureDishesRouting() {
    val dishesController = DishesController()

    routing {
        get("/dishes", {
            tags = listOf("Dishes")
            description = "Get all dishes"
            response {
                HttpStatusCode.OK to {
                    description = "Successful"
                    body<DishesRespondRemote>()
                }
            }
        }) {
            dishesController.performGetAllDishes(call)
        }

        get("/dishes/{category}", {
            tags = listOf("Dishes")
            description = "Get all dishes by category"
            request {
                pathParameter<String>("category")
            }
            response {
                HttpStatusCode.OK to {
                    description = "Successful"
                    body<DishesRespondRemote>()
                }
            }
        }) {
            dishesController.performGetDishesByCategory(call)
        }
    }
}
