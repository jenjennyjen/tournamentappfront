package com.example.tournamentapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tournamentapp.data.ApiClient
import com.example.tournamentapp.data.TournamentDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun TournamentDetailsScreen(
    tournamentId: Int,
    onBack: () -> Unit
) {
    // State variables for UI
    var tournamentDetails by remember { mutableStateOf<TournamentDetails?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Fetch tournament details
    LaunchedEffect(tournamentId) {
        isLoading = true
        errorMessage = null
        ApiClient.apiService.getTournamentDetails(tournamentId).enqueue(object : Callback<TournamentDetails> {
            override fun onResponse(call: Call<TournamentDetails>, response: Response<TournamentDetails>) {
                if (response.isSuccessful) {
                    tournamentDetails = response.body()
                } else {
                    errorMessage = "Failed to fetch details: ${response.message()}"
                }
                isLoading = false
            }

            override fun onFailure(call: Call<TournamentDetails>, t: Throwable) {
                errorMessage = "Network error: ${t.message}"
                isLoading = false
            }
        })
    }

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Back button
        Button(onClick = onBack, modifier = Modifier.padding(bottom = 16.dp)) {
            Text("Back")
        }

        // Display content based on state
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage.orEmpty(),
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            tournamentDetails != null -> {
                val tournament = tournamentDetails!!

                // Tournament Name
                Text(
                    text = tournament.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Tournament Image
                AsyncImage(
                    model = tournament.imageUrl,
                    contentDescription = "Tournament Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(bottom = 16.dp)
                )

                // Tournament Details
                Text(text = "Description: ${tournament.description ?: "No description"}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Start Date: ${tournament.startDate ?: "Unknown"}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "End Date: ${tournament.endDate ?: "Unknown"}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Location: ${tournament.location ?: "Unknown"}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))

                // Completed Status
                val statusText = if (tournament.complete == true) "Completed" else "Ongoing"
                val statusColor = if (tournament.complete == true) Color.Green else Color.Yellow
                Text(
                    text = "Status: $statusText",
                    color = statusColor,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Buttons
                Button(
                    onClick = { /* Save tournament */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save Tournament")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* Join tournament */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Join Tournament")
                }
            }
        }
    }
}
