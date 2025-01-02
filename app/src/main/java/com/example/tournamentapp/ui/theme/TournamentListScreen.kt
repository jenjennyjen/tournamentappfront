//package com.example.tournamentapp.ui.theme
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import com.example.tournamentapp.data.ApiClient
//import com.example.tournamentapp.data.Tournament
//
//@Composable
//fun TournamentListScreen(onTournamentClick: (Int) -> Unit) {
//    var tournaments by remember { mutableStateOf<List<Tournament>>(emptyList()) }
//    var isLoading by remember { mutableStateOf(true) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    LaunchedEffect("tournamentList") {
//        isLoading = true
//        errorMessage = null
//        try {
//            tournaments = ApiClient.apiService.getTournaments()
//        } catch (e: Exception) {
//            e.printStackTrace()
//            errorMessage = "Failed to load tournaments. Please try again later."
//        } finally {
//            isLoading = false
//        }
//    }
//
//    when {
//        isLoading -> {
//            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
//        }
//        errorMessage != null -> {
//            Text(
//                text = errorMessage.orEmpty(),
//                color = Color.Red,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//            )
//        }
//        tournaments.isEmpty() -> {
//            Text(
//                text = "No tournaments available.",
//                color = Color.Gray,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(16.dp)
//            )
//        }
//        else -> {
//            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
//                items(tournaments) { tournament ->
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable { onTournamentClick(tournament.id) }
//                            .padding(vertical = 8.dp)
//                    ) {
//                        Text(text = tournament.name, modifier = Modifier.weight(1f))
//                        Text(text = "ID: ${tournament.id}")
//                    }
//                }
//            }
//        }
//    }
//}
