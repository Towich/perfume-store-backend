package com.towich.database.restaurants

import com.towich.features.restaurants.RestaurantRespond
import com.towich.other.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class RestaurantsDTO(
    val id: @Serializable(with = UUIDSerializer::class) UUID,
    val city: String,
    val street: String,
    val house: String,
    val latitude: Float,
    val longitude: Float
){
    fun convertToGetAllRestaurantsRespond() = RestaurantRespond(
        id, city, street, house, latitude, longitude
    )
}
