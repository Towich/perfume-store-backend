package com.towich.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.UUID


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respond("hi.")
        }
    }
}
