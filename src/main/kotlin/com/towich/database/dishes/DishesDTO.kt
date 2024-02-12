package com.towich.database.dishes

import com.towich.features.dishes.DishesRespondRemote
import com.towich.other.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class DishesDTO(
    val id: @Serializable(with = UUIDSerializer::class) UUID,
    val category: String,
    val name: String,
    val image_source: String,
    val price: Int,
    val grams: Int,
    val time_to_cook: Int,
    val rating: Float,
    val ordered_times: Int,
    val description: String
){
//    fun castToDishesRespondRemote(): DishesRespondRemote = DishesRespondRemote(
//        id = id,
//        category = category,
//        name = name,
//        image_source = image_source,
//        price = price,
//        grams = grams,
//        time_to_cook = time_to_cook,
//        rating = rating
//    )
}