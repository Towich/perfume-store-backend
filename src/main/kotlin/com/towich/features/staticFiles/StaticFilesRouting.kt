package com.towich.features.staticFiles

import io.github.smiley4.ktorswaggerui.dsl.get
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import java.io.File

fun Application.configureStaticFiles() {
    routing {
        get() {

        }
        staticFiles("/files", File("files")){

        }
    }
}