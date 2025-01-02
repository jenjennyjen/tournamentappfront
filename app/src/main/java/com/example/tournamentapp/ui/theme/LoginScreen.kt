package com.example.tournamentapp.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tournamentapp.data.ApiClient
import com.example.tournamentapp.data.AuthResponse
import com.example.tournamentapp.data.LoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onLoginSuccess: (String) -> Unit // Pass JWT token on success
) {
    var usernameOrEmail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) } // Show loading indicator

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text("Login", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Username or Email Field
        OutlinedTextField(
            value = usernameOrEmail,
            onValueChange = { usernameOrEmail = it },
            label = { Text("Username or Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = usernameOrEmail.isEmpty() && errorMessage != null
        )
        if (usernameOrEmail.isEmpty() && errorMessage != null) {
            Text(
                text = "Username or Email is required",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            isError = password.isEmpty() && errorMessage != null
        )
        if (password.isEmpty() && errorMessage != null) {
            Text(
                text = "Password is required",
                color = MaterialTheme.colorScheme.error,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = {
                if (usernameOrEmail.isEmpty() || password.isEmpty()) {
                    errorMessage = "Please fill in all fields"
                    return@Button
                }

                isLoading = true
                val request = if (usernameOrEmail.contains("@")) {
                    LoginRequest(username = null, email = usernameOrEmail, password = password)
                } else {
                    LoginRequest(username = usernameOrEmail, email = null, password = password)
                }

                ApiClient.apiService.login(request).enqueue(object : Callback<AuthResponse> {
                    override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                        isLoading = false
                        if (response.isSuccessful && response.body()?.success == true) {
                            val token = response.body()?.data?.token
                            Log.d("Login", "JWT Token: $token")
                            onLoginSuccess(token ?: "")
                        } else {
                            errorMessage = response.body()?.message ?: "Login failed. Please try again."
                            Log.e("Login", "Error: ${response.body()?.message}")
                        }
                    }

                    override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                        isLoading = false
                        errorMessage = "Network error: ${t.message}"
                        Log.e("Login", "Failure: ${t.message}")
                    }
                })
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading // Disable button when loading
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary, modifier = Modifier.size(18.dp))
            } else {
                Text("Login")
            }
        }

        // Error Message
        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        // Navigate to Register
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = onNavigateToRegister) {
            Text("Don't have an account? Register")
        }
    }
}
