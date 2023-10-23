package com.towich.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Test(
    val owner: String,
    val text: String
)

fun Application.configureRouting() {
    routing {
        get("/getOwner") {
            call.respond(Test(owner = "Towich", text = UUID.randomUUID().toString()))
        }
    }
}
