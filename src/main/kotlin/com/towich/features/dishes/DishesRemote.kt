package com.towich.features.dishes

import kotlinx.serialization.Serializable

@Serializable
data class DishesReceiveRemote(
    val category: String,
)

@Serializable
data class DishesRespondRemote(
    val id: String,
    val category: String,
    val name: String,
    val image: String,
    val price: Int,
    val grams: Int,
    val time_to_cook: Int,
    val rating: Float
)