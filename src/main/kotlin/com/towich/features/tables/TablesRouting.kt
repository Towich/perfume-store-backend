package com.towich.features.tables

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureTablesRouting() {
    val tablesController = TablesController()

    routing {
        get("/tables"){
//            tablesController.performGetTablesByRestaurant()
        }
        get("/tables/{restaurant_id}") {
            tablesController.performGetTablesByRestaurant(call)
        }
    }
}
