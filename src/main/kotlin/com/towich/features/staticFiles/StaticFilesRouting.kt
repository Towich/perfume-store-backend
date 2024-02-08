package com.towich.features.staticFiles

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureStaticFiles() {
    routing {
        staticFiles("/files", File("files"))
    }
}