package com.towich.features.register

import com.towich.cache.InMemoryCache
import com.towich.cache.TokenCache
import com.towich.database.tokens.Tokens
import com.towich.database.tokens.TokensDTO
import com.towich.database.users.UserDTO
import com.towich.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {

        // Получаем данные и десериализуем их в объект класса RegisterReceiveRemote
        val receivedData = call.receive(RegisterReceiveRemote::class)

        // Получаем модель пользователя из базы данных
        val userDTO: UserDTO? = Users.fetchUser(receivedData.email)

        // Если такой пользователь не существует в базе данных
        if (userDTO != null) {
            call.respond(HttpStatusCode.BadRequest, "This account has already registered!")
        } else {

            // Добавляем пользователя в базу данных
            Users.insert(
                UserDTO(
                    id = UUID.randomUUID().toString(),
                    email = receivedData.email,
                    password = receivedData.password,
                    username = "testname"
                )
            )

            // Создаем и добавляем токен в базу данных
            val newToken = UUID.randomUUID().toString()
            Tokens.insert(
                TokensDTO(
                    id = UUID.randomUUID().toString(),
                    email = receivedData.email,
                    token = newToken
                )
            )

            // Выдаем созданный токен в качестве ответа
            call.respond(RegisterRespondRemote(token = newToken))
        }
    }
}