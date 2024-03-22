package com.towich.features.tables

import com.towich.features.dishes.DishesRespondRemote
import io.github.smiley4.ktorswaggerui.dsl.get
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureTablesRouting() {
    val tablesController = TablesController()

    routing {
        get("/tables/{restaurant_id}", {
            tags = listOf("Tables")
            description = "Get all tables by restaurant id"
            request {
                pathParameter<String>("restaurant_id")
            }
            response {
                HttpStatusCode.OK to {
                    description = "Successful"
                    body<TablesRespondRemote>()
                }
                HttpStatusCode.BadRequest to {
                    description = "Illegal restaurant id"
                }
            }
        }) {
            tablesController.performGetTablesByRestaurantId(call)
        }

        get("/table/{table_id}", {
            tags = listOf("Tables")
            description = "Get all tables by restaurant id"
            request {
                pathParameter<String>("table_id")
            }
            response {
                HttpStatusCode.OK to {
                    description = "Successful"
                    body<TablesRespondRemote>()
                }
                HttpStatusCode.BadRequest to {
                    description = "Illegal table id"
                }
                HttpStatusCode.NotFound to {
                    description = "Table not found"
                }
            }
        }) {
            tablesController.performGetTableById(call)
        }

        post("/table", {
            tags = listOf("Tables")
            description = "Change table's is_free"
            request {
                body<TablesRequestChangeIsFreeRemote>()
            }
            response {
                HttpStatusCode.OK to {
                    description = "Successful"
                }
                HttpStatusCode.NotModified to {
                    description = "Not Modified"
                }
            }
        }) {
            tablesController.performUpdateTableIsFreeById(call)
        }
    }
}
