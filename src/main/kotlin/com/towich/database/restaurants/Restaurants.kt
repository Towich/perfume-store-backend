package com.towich.database.restaurants

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Restaurants: Table("restaurants") {
    private val id = Restaurants.uuid("id")
    private val city = Restaurants.varchar("city", 50)
    private val street = Restaurants.varchar("street", 50)
    private val house = Restaurants.varchar("house", 25)
    private val latitude = Restaurants.float("latitude")
    private val longitude = Restaurants.float("longitude")

    private fun resultRowToRestaurant(row: ResultRow) = RestaurantsDTO(
        id = row[id],
        city = row[city],
        street = row[street],
        house = row[house],
        latitude = row[latitude],
        longitude = row[longitude]
    )

    fun getAllRestaurants(): List<RestaurantsDTO> = transaction {
        Restaurants
            .selectAll()
            .map(::resultRowToRestaurant)
    }
}