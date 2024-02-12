package com.towich.database.dishes_ingredients

import com.towich.database.ingredients.IngredientsDTO
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

object DishesIngredients: Table("dishes_ingredients") {
    private val id = DishesIngredients.uuid("id")
    private val dish_id = DishesIngredients.uuid("dish_id")
    private val ingredient_name = DishesIngredients.varchar("ingredient_name", 50)

    private fun resultRowToDish(row: ResultRow) = DishesIngredientsDTO(
        id = row[id],
        dish_id = row[dish_id],
        ingredient_name = row[ingredient_name]
    )

    fun getAllIngredientsByDishId(dish_id: UUID): List<DishesIngredientsDTO> = transaction {
        DishesIngredients
            .select { DishesIngredients.dish_id eq dish_id }
            .map(::resultRowToDish)
    }
}