package com.towich

import com.towich.features.login.configureLoginRouting
import com.towich.features.register.configureRegisterRouting
import com.towich.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {

    // Подключаемся к базе данных
    Database.connect(
        url = "jdbc:postgresql://${Constants.DATABASE_REMOTE_URL}",
        driver = "org.postgresql.Driver",
        user = Constants.DATABASE_USER,
        password = Constants.DATABASE_PASSWORD
    )

    // Запускаем бэк
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
}
