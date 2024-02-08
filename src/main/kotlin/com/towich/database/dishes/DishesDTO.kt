package com.towich.database.dishes

import com.towich.features.dishes.DishesRespondRemote
import kotlinx.serialization.Serializable

@Serializable
data class DishesDTO(
    val id: String,
    val category: String,
    val name: String,
    val image: String,
    val price: Int,
    val grams: Int,
    val time_to_cook: Int,
    val rating: Float

){
    fun castToDishesRespondRemote(): DishesRespondRemote = DishesRespondRemote(
        id = id,
        category = category,
        name = name,
        image = image,
        price = price,
        grams = grams,
        time_to_cook = time_to_cook,
        rating = rating
    )
}