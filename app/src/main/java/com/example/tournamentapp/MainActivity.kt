package com.example.tournamentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
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
    NavHost(navController = navController, startDestination = "welcome") {
        // welcome Page
        composable("welcome") {
            WelcomeScreen(
                onLoginClick = { navController.navigate("login") },
                onSignupClick = { navController.navigate("register") }
            )
        }

        // home Page
        composable("home") {
            HomeScreen(
                onLoginClick = { navController.navigate("login") },
                onSignupClick = { navController.navigate("register") }
            )
        }

        // login Page
        composable("login") {
            LoginScreen(
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("home") }
            )
        }

        // register Page
        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("home") }
            )
        }
    }
}
