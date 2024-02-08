package com.towich.database.dishes

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Dishes: Table("dishes") {
    private val id = Dishes.varchar("id", 36)
    private val name = Dishes.varchar("name", 50)
    private val image = Dishes.varchar("image", 50)
    private val price = Dishes.integer("price")
    private val grams = Dishes.integer("grams")
    private val time_to_cook = Dishes.integer("time_to_cook")
    private val rating = Dishes.float("rating")
    private val category = Dishes.varchar("category", 50)

    private fun resultRowToDish(row: ResultRow) = DishesDTO(
        id = row[Dishes.id],
        name = row[Dishes.name],
        image = row[Dishes.image],
        price = row[Dishes.price],
        grams = row[Dishes.grams],
        time_to_cook = row[Dishes.time_to_cook],
        rating = row[Dishes.rating],
        category = row[Dishes.category]
    )

    fun allDishesByCategory(category: String): List<DishesDTO> = transaction {
        Dishes.select { Dishes.category eq category }
            .map(::resultRowToDish)
    }
}