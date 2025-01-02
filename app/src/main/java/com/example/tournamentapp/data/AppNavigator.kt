package com.example.tournamentapp.ui.theme


import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        // Home Screen
        composable("home") {
            HomeScreen(
                onTournamentClick = { tournament ->
                    navController.navigate("details/$tournament.id")
                }
            )
        }
        composable("details/{tournament.id}") { backStackEntry ->
            val tournamentId = backStackEntry.arguments?.getString("tournamentId")?.toIntOrNull()
            if (tournamentId != null) {
                TournamentDetailsScreen(
                    tournamentId = tournamentId,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

