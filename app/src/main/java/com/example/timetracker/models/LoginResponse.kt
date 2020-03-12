package com.example.timetracker.models

data class LoginResponse(
    val key: String
)

data class LoginRequest(
    var username: String,
    val password: String
)