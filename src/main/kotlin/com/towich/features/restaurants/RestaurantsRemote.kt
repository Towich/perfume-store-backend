package com.towich.features.restaurants

import com.towich.other.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class RestaurantRespond(
    val id: @Serializable(with = UUIDSerializer::class) UUID,
    val city: String,
    val street: String,
    val house: String,
    val latitude: Float,
    val longitude: Float
)
