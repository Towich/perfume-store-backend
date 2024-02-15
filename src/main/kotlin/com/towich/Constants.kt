package com.towich

object Constants {
    val DATABASE_REMOTE_URL: String = System.getenv("DATABASE_REMOTE_URL")
    val DATABASE_USER: String = System.getenv("DATABASE_USER")
    val DATABASE_PASSWORD: String = System.getenv("DATABASE_PASSWORD")

    val AES_CRYPTO_KEY: String = System.getenv("AES_CRYPTO_KEY")
}