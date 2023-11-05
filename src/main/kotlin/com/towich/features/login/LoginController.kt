package com.towich.features.login

import com.towich.cache.InMemoryCache
import com.towich.cache.TokenCache
import com.towich.database.tokens.Tokens
import com.towich.database.tokens.TokensDTO
import com.towich.database.users.UserDTO
import com.towich.database.users.Users
import com.towich.features.register.RegisterRespondRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.*

class LoginController(private val call: ApplicationCall) {

    suspend fun performLogin(){

        // Получаем данные и десериализуем их в объект класса LoginReceiveRemote
        val receivedData = call.receive(LoginReceiveRemote::class)

        val userDTO: UserDTO? = Users.fetchUser(receivedData.email)

        // Если такой пользователь существует в базе данных
        if (userDTO != null)
        {
            // Если входящий пароль == паролю в базе данных
            if(receivedData.password == userDTO.password){

                // Создаем и добавляем в таблицу 'tokens' новый токен
                // и выдаем его в качестве ответа объектом LoginRespondRemote
                val newToken = UUID.randomUUID().toString()
                Tokens.insert(
                    TokensDTO(
                        id = UUID.randomUUID().toString(),
                        email = receivedData.email,
                        token = newToken
                    )
                )

                // Выдаем созданный токен в качестве ответа
                call.respond(LoginRespondRemote(token = newToken))
            }
            else{
                // иначе дропаем ошибку
                call.respond(HttpStatusCode.BadRequest, "Invalid password!")
            }
        }
        else{
            // иначе дропаем ошибку
            call.respond(HttpStatusCode.BadRequest, "That user don't exists!")
        }
    }
}