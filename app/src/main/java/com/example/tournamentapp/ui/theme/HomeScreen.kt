package com.example.tournamentapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape

@Composable
fun HomeScreen(
    onLoginClick: () -> Unit,
    onSignupClick: () -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3D3938))
            .padding(8.dp)
    ) {
        // top bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SearchBar(searchText) { searchText = it }
            Row {
                IconButton(onClick = { /* profile */ }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Profile",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* menu */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // near me
        Section(title = "Near Me")

        // favorites
        Section(title = "Favorites")

        // placeholder
        Section(title = "Placeholder")

        Spacer(modifier = Modifier.height(32.dp))

        // buttons for Login and Sign-up
        Button(
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C6A66)),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(48.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Login", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onSignupClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6C6A66)),
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(48.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text("Sign-up", color = Color.White)
        }

        Spacer(modifier = Modifier.weight(1f))

        // bottom Navigation Bar
        BottomNavigationBar()
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
fun Section(title: String) {
    Text(
        text = title,
        color = Color.White,
        fontSize = 18.sp,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp)
    )

    LazyRow {
        items(5) { // placeholder for 5 items below
            Box(
                modifier = Modifier
                    .size(120.dp, 160.dp)
                    .padding(8.dp)
                    .clip(AbsoluteRoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            )
        }
    }
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
            BottomNavIcon("⬜") // placeholder icons
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
