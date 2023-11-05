package com.towich.database.users

data class UserDTO(
    val id: String,
    val email: String,
    val password: String,
    val username: String
)