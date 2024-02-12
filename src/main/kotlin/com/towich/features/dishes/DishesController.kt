package com.towich.features.dishes

import com.towich.database.dishes.Dishes
import com.towich.database.dishes.DishesDTO
import com.towich.database.dishes_ingredients.DishesIngredients
import io.ktor.server.application.*
import io.ktor.server.response.*

class DishesController {

    suspend fun performGetAllDishes(call: ApplicationCall) {
        val respondDishes = mutableListOf<DishesRespondRemote>()

        val listOfAllDishes: List<DishesDTO> = Dishes.allDishes()
        for (dish in listOfAllDishes) {
            val listOfIngredients: List<String> =
                DishesIngredients.getAllIngredientsByDishId(dish.id).map { it.ingredient_name }
            respondDishes.add(
                DishesRespondRemote(
                    id = dish.id,
                    category = dish.category,
                    name = dish.name,
                    image_source = dish.image_source,
                    price = dish.price,
                    grams = dish.grams,
                    time_to_cook = dish.time_to_cook,
                    rating = dish.rating,
                    ordered_times = dish.ordered_times,
                    description = dish.description,
                    ingredients = listOfIngredients
                )
            )
        }

        call.respond(respondDishes)
    }

    suspend fun performGetDishesByCategory(call: ApplicationCall) {
        val respondDishes = mutableListOf<DishesRespondRemote>()

        val listOfAllDishes: List<DishesDTO> = Dishes.allDishesByCategory(call.parameters["category"] ?: "null")
        for (dish in listOfAllDishes) {
            val listOfIngredients: List<String> =
                DishesIngredients.getAllIngredientsByDishId(dish.id).map { it.ingredient_name }
            respondDishes.add(
                DishesRespondRemote(
                    id = dish.id,
                    category = dish.category,
                    name = dish.name,
                    image_source = dish.image_source,
                    price = dish.price,
                    grams = dish.grams,
                    time_to_cook = dish.time_to_cook,
                    rating = dish.rating,
                    ordered_times = dish.ordered_times,
                    description = dish.description,
                    ingredients = listOfIngredients
                )
            )
        }

        call.respond(respondDishes)
    }
}