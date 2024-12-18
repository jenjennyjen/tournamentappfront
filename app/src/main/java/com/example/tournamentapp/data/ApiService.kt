package com.example.tournamentapp.data

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("api/login")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @POST("api/signup")
    fun signup(@Body request: SignupRequest): Call<AuthResponse>
}
