package com.towich.features.login

import io.github.smiley4.ktorswaggerui.dsl.get
import io.github.smiley4.ktorswaggerui.dsl.post
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureLoginRouting() {
    routing {
        post("/login", {
            tags = listOf("Authorization")
            description = "Authorize a user"
            request {
                body<LoginReceiveRemote>()
            }
            response {
                HttpStatusCode.OK to {
                    description = "User logged in"
                    body<LoginRespondRemote>()
                }
                HttpStatusCode.BadRequest to {
                    description = "Invalid password"
                }

                HttpStatusCode.NotFound to {
                    description = "That user don't exists"
                }
            }
        }) {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}
