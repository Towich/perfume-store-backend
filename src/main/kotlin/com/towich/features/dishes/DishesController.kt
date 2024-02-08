package com.towich.features.dishes

import com.towich.database.dishes.Dishes
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

class DishesController() {

    suspend fun performGetDishes(call: ApplicationCall) {
        val receivedData = call.receive(DishesReceiveRemote::class)

        val listOfAllDishes: List<DishesRespondRemote> = Dishes.allDishesByCategory(receivedData.category).map { it.castToDishesRespondRemote() }

        call.respond(listOfAllDishes)
    }
}