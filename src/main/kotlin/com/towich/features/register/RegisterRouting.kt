package com.towich.features.register

import com.towich.cache.InMemoryCache
import com.towich.cache.TokenCache
import com.towich.features.login.LoginReceiveRemote
import com.towich.features.login.LoginRespondRemote
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.util.UUID

fun Application.configureRegisterRouting() {
    routing {
        post("/register", {
            tags = listOf("Authorization")
            description = "Register a user"
            request {
                body<RegisterReceiveRemote>()
            }
            response {
                HttpStatusCode.OK to {
                    description = "User registered"
                    body<RegisterRespondRemote>()
                }
                HttpStatusCode.BadRequest to {
                    description = "This account has already registered"
                }
            }
        }) {
            val registerController = RegisterController(call)
            registerController.registerNewUser()
        }
    }
}
