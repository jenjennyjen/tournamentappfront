package com.example.tournamentapp.data

data class AuthResponse(
    val success: Boolean,
    val data: AuthData? = null, // Nullable to handle cases where it's missing
    val message: String? = null // Nullable for error messages
)

data class AuthData(
    val userId: Int?,
    val email: String?,
    val token: String?
)


// Request model for login
data class LoginRequest(
    val username: String?, // Username is optional because the user may log in with an email instead
    val email: String?,    // Email is optional because the user may log in with a username instead
    val password: String
)

// Request model for signup (optional)
data class SignupRequest(
    val username: String,
    val email: String,
    val password: String
)
