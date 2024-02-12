package com.towich.features.dishes

import com.towich.other.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class DishesReceiveRemote(
    val category: String,
)

@Serializable
data class DishesRespondRemote(
    val id: @Serializable(with = UUIDSerializer::class) UUID,
    val category: String,
    val name: String,
    val image_source: String,
    val price: Int,
    val grams: Int,
    val time_to_cook: Int,
    val rating: Float,
    val ordered_times: Int,
    val description: String,
    val ingredients: List<String>
)