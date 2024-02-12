package com.towich.database.dishes

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Dishes: Table("dishes") {
    private val id = Dishes.uuid("id")
    private val category = Dishes.varchar("category", 25)
    private val name = Dishes.varchar("name", 50)
    private val image_source = Dishes.varchar("image_source", 50)
    private val price = Dishes.integer("price")
    private val grams = Dishes.integer("grams")
    private val time_to_cook = Dishes.integer("time_to_cook")
    private val rating = Dishes.float("rating")
    private val ordered_times = Dishes.integer("ordered_times")
    private val description = Dishes.varchar("description", 50)


    private fun resultRowToDish(row: ResultRow) = DishesDTO(
        id = row[id],
        category = row[category],
        name = row[name],
        image_source = row[image_source],
        price = row[price],
        grams = row[grams],
        time_to_cook = row[time_to_cook],
        rating = row[rating],
        ordered_times = row[ordered_times],
        description = row[description]
    )

    fun allDishes(): List<DishesDTO> = transaction {
        Dishes.selectAll()
            .map(::resultRowToDish)
    }

    fun allDishesByCategory(category: String): List<DishesDTO> = transaction {
        Dishes.select { Dishes.category eq category }
            .map(::resultRowToDish)
    }
}