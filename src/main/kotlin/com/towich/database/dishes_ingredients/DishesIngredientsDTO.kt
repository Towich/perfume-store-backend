package com.towich.database.dishes_ingredients

import com.towich.other.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class DishesIngredientsDTO(
    val id: @Serializable(with = UUIDSerializer::class) UUID,
    val dish_id: @Serializable(with = UUIDSerializer::class) UUID,
    val ingredient_name: String
)
