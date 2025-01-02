package com.example.tournamentapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tournamentapp.data.ApiClient
import java.util.Locale


@Composable
fun HomeScreen(
    onTournamentClick: (Int) -> Unit // Navigate to TournamentDetailsScreen
) {
    // States
    var tournaments by remember { mutableStateOf<List<Tournament>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var selectedType by remember { mutableStateOf("all") }

    // Fetch data when `selectedType` changes
    LaunchedEffect(selectedType) {
        isLoading = true
        errorMessage = null
        try {
            val response = ApiClient.apiService.getTournamentList(selectedType.lowercase()).execute()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                if (apiResponse?.success == true) {
                    tournaments = apiResponse.body
                } else {
                    errorMessage = "Failed to fetch tournaments."
                }
            } else {
                errorMessage = "Failed to fetch tournaments: ${response.message()}"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage = "Network error: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3D3938))
            .padding(8.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar("") { /* Handle Search */ }
            Row {
                IconButton(onClick = { /* Profile Action */ }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* Menu Action */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Tournament Sections
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
            errorMessage != null -> {
                Text(
                    text = errorMessage.orEmpty(),
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
            tournaments.isNotEmpty() -> {
                Section(
                    title = selectedType.replaceFirstChar { it.titlecase(Locale.ROOT) },
                    tournaments = tournaments,
                    onTournamentClick = onTournamentClick
                )
            }

            else -> {
                Text(
                    text = "No tournaments available.",
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation
        BottomNavigationBar()
    }
}

@Composable
fun Section(
    title: String,
    tournaments: List<Tournament>,
    onTournamentClick: (Int) -> Unit
) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 18.sp,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
    )

    LazyRow {
        items(tournaments) { tournament ->
            Box(
                modifier = Modifier
                    .size(200.dp, 160.dp)
                    .padding(8.dp)
                    .background(Color.LightGray)
                    .clickable { onTournamentClick(tournament.id) } // Pass the ID to the click handler
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = tournament.name,
                        color = Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Created: ${tournament.createDate ?: "N/A"}",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                    tournament.startDate?.let {
                        Text(
                            text = "Starts: $it",
                            color = Color.Gray,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color.Gray)
                Box(modifier = Modifier.padding(start = 8.dp)) { innerTextField() }
            }
        }
    )
}

@Composable
fun BottomNavigationBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavIcon("🔥")
        repeat(4) {
            BottomNavIcon("⬜") // Placeholder icons
        }
    }
}

@Composable
fun BottomNavIcon(icon: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(text = icon, fontSize = 20.sp)
    }
}
