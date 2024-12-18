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
import com.example.tournamentapp.data.SignupRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit) {
    // User input states
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Main UI layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text("Register", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))

        // Username input
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Email input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Password input
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Confirm password input
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Register button
        Button(
            onClick = {
                if (password == confirmPassword) {
                    // Create the SignupRequest object
                    val request = SignupRequest(username, email, password)

                    // API call to register
                    ApiClient.apiService.signup(request).enqueue(object : Callback<AuthResponse> {
                        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                            if (response.isSuccessful && response.body()?.success == true) {
                                Log.d("Register", "User registered successfully")
                                onRegisterSuccess()
                            } else {
                                errorMessage = response.body()?.message ?: "Registration failed"
                            }
                        }

                        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                            errorMessage = "Network error: ${t.message}"
                            Log.e("Register", "Error: ${t.message}")
                        }
                    })
                } else {
                    errorMessage = "Passwords do not match"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register")
        }

        // Error message display
        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
