package com.example.tournamentapp.data

data class TournamentDetails(
    val id: Int,
    val name: String,
    val description: String?,
    val startDate: String?,
    val endDate: String?,
    val location: String?,
    val imageUrl: String?,
    val complete: Boolean
)
