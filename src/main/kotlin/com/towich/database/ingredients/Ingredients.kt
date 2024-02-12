package com.towich.database.ingredients

import com.towich.database.dishes_ingredients.DishesIngredients
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Ingredients: Table("ingredients") {
    private val name = DishesIngredients.varchar("name", 50)

    private fun resultRowToDish(row: ResultRow) = IngredientsDTO(
        name = row[name]
    )
}