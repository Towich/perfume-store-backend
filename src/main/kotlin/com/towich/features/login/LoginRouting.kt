package com.towich.features.login

import com.towich.cache.InMemoryCache
import com.towich.cache.TokenCache
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureLoginRouting() {
    routing {
        get("/login") {

            // Получаем данные и десериализуем их в объект класса LoginReceiveRemote
            val receivedData = call.receive(LoginReceiveRemote::class)

            // Если такие логин и пароль существуют в базе данных
            if (InMemoryCache.userList.map { it.login }
                    .contains(receivedData.login) && InMemoryCache.userList.map { it.password }
                    .contains(receivedData.password)
            ){
                // то ищем и выдаем токен в качестве ответа объект класса LoginRespondRemote
                val token = InMemoryCache.token.first() { it.login == receivedData.login }.token
                InMemoryCache.token.add(TokenCache(login = receivedData.login, token = token))
                call.respond(LoginRespondRemote(token = token))
            }
            else{
                // иначе дропаем ошибку
                call.respond(HttpStatusCode.BadRequest, "Invalid password!")
            }
        }
    }
}
