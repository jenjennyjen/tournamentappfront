package com.example.tournamentapp.data
import com.example.tournamentapp.data.AuthRequest
import com.example.tournamentapp.data.AuthResponse


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(".")
    fun login(@Body request: AuthRequest): Call<AuthResponse>

    @POST(".")
    fun register(@Body request: AuthRequest): Call<AuthResponse>
}
