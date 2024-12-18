package com.example.tournamentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.tournamentapp.ui.theme.HomeScreen
import com.example.tournamentapp.ui.theme.LoginScreen
import com.example.tournamentapp.ui.theme.RegisterScreen
import com.example.tournamentapp.ui.theme.WelcomeScreen
import com.example.tournamentapp.ui.theme.TournamentAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TournamentAppTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome" // WelcomeScreen is the initial screen
    ) {
        // Welcome Screen
        composable("welcome") {
            WelcomeScreen(
                onLoginClick = { navController.navigate("login") },
                onSignupClick = { navController.navigate("register") }
            )
        }

        // Login Screen
        composable("login") {
            LoginScreen(
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { token ->
                    // Save the JWT token or pass it to HomeScreen
                    navController.navigate("home") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }

        // Register Screen
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = {
                    // Navigate to the LoginScreen after successful registration
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                }
            )
        }

        // Home Screen
        composable("home") {
            HomeScreen(
                onLoginClick = { navController.navigate("login") },
                onSignupClick = { navController.navigate("register") }
            )
        }
    }
}
