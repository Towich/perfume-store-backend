package com.towich.features.dishes

import io.ktor.server.application.*

interface IDishesController {
    suspend fun performGetAllDishes(call: ApplicationCall)
    suspend fun performGetDishesByCategory(call: ApplicationCall)
}