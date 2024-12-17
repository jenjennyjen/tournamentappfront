package com.example.tournamentapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tournamentapp.data.MockData

@Composable
fun TournamentDetailsScreen(tournamentId: String?) {

    val tournament = MockData.mockTournaments.find { it.id == tournamentId }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        if (tournament != null) {
            Text(
                text = tournament.name,
                fontSize = 24.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            // add tournament details
            Text(
                text = "Tournament ID: ${tournament.id}",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "This is where more details about ${tournament.name} will go.",
                fontSize = 14.sp
            )
        } else {
            // display a message if the tournament is not found
            Text(
                text = "Tournament not found!",
                fontSize = 18.sp
            )
        }
    }
}
