package com.example.idnow_v2application.network

data class JWTData (
    val id: Long,
    val firstName: String,
    val lastName: String,
    val username: String,
    val gender: String,
    val email: String,
    val image: String,
    val token: String
)