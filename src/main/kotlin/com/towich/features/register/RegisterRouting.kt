package com.towich.features.register

import com.towich.cache.InMemoryCache
import com.towich.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureRegisterRouting() {
    routing {
        get("/register") {

            // Получаем данные и десериализуем их в объект класса RegisterReceiveRemote
            val receivedData = call.receive(RegisterReceiveRemote::class)

            // Если такой логин не существует в базе данных
            if (!InMemoryCache.userList.map { it.login }.contains(receivedData.login)){

                // Добавляем пользователя в базу данных
                InMemoryCache.userList.add(receivedData)

                // Создаем токен и выдаем в качестве ответа объект класса RegisterRespondRemote
                val token = UUID.randomUUID().toString()
                InMemoryCache.token.add(TokenCache(login = receivedData.login, token = token))
                call.respond(RegisterRespondRemote(token = token))
            }
            else{
                // иначе дропаем ошибку
                call.respond(HttpStatusCode.BadRequest, "This account has already registered!")
            }
        }
    }
}
