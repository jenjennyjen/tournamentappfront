package com.example.tournamentapp.data

data class UserResponse(
    val id: String,
    val username: String,
    val photo: String,
    val bio: String
)

data class TournamentSummary(
    val id: String,
    val name: String,
    val image: String
)

object MockData {
    val mockUser = UserResponse(
        id = "1",
        username = "John Doe",
        photo = "https://example.com/photo.jpg",
        bio = "Gamer | Developer | Tournament Enthusiast"
    )

    val mockTournaments = listOf(
        TournamentSummary("t1", "Chess Championship", "https://example.com/chess.jpg"),
        TournamentSummary("t2", "Football League", "https://example.com/football.jpg"),
        TournamentSummary("t3", "Esports Arena", "https://example.com/esports.jpg")
    )
}
