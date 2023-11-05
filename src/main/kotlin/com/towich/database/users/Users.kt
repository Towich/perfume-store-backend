package com.towich.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users : Table("users") {
    private val id = Users.varchar("id", 50)
    private val email = Users.varchar("email", 25)
    private val password = Users.varchar("password", 25)
    private val username = Users.varchar("username", 25)

    fun insert(userDTO: UserDTO) {
        transaction {
            Users.insert {
                it[id] = userDTO.id
                it[email] = userDTO.email
                it[password] = userDTO.password
                it[username] = userDTO.username
            }
        }
    }

    fun fetchUser(email: String): UserDTO? = transaction {
        val userModel = Users.select {Users.email eq email }.firstOrNull() ?: return@transaction null

        return@transaction UserDTO(
            id = userModel[Users.id],
            email = userModel[Users.email],
            password = userModel[Users.password],
            username = userModel[Users.username]
        )
    }
}