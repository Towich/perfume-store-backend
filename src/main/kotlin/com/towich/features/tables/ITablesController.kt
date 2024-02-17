package com.towich.features.tables

import io.ktor.server.application.*

interface ITablesController {
    suspend fun performGetTablesByRestaurantId(call: ApplicationCall)
    suspend fun performGetTableById(call: ApplicationCall)
    suspend fun performUpdateTableIsFreeById(call: ApplicationCall)
}