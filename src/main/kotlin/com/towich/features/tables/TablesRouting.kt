package com.towich.features.tables

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureTablesRouting() {
    val tablesController = TablesController()

    routing {
        get("/tables/{restaurant_id}") {
            tablesController.performGetTablesByRestaurantId(call)
        }
        get("/table/{table_id}"){
            tablesController.performGetTableById(call)
        }
        post("/table"){
            tablesController.performUpdateTableIsFreeById(call)
        }
    }
}
