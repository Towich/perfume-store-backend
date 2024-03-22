package com.towich.plugins

import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        get("/", {
            description = "Test"
        }) {
            call.respond("hi.")
        }
    }
}
