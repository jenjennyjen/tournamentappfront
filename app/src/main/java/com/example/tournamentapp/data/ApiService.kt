package com.example.tournamentapp.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("api/signup")
    fun signup(@Body request: SignupRequest): Call<AuthResponse>

    @GET("api/list/{type}")
    fun getTournamentList(@Path("type") type: String): Call<ApiResponse<List<Tournament>>>

    @GET("api/tournament/{id}")
    fun getTournamentDetails(@Path("id") id: Int): Call<TournamentDetails>


}
