package com.example.tournamentapp.data

data class AuthResponse(
    val success: Boolean,
    val data: AuthData? = null,
    val message: String? = null
)

data class AuthData(
    val userId: Int?,
    val email: String?,
    val token: String?
)


// Request model for login
data class LoginRequest(
    val username: String?,
    val email: String?,
    val password: String
)

// Request model for signup (optional)
data class SignupRequest(
    val username: String,
    val email: String,
    val password: String
)
