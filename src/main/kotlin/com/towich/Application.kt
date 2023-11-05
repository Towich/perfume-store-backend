package com.towich

import com.towich.features.login.configureLoginRouting
import com.towich.features.register.configureRegisterRouting
import com.towich.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.jetbrains.exposed.sql.Database

fun main() {

    // ������������ � ���� ������
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/restaurant",
        driver = "org.postgresql.Driver",
        user = Confidentials.databaseUser,
        password = Confidentials.databasePassword
    )

    // ��������� ���
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
}
