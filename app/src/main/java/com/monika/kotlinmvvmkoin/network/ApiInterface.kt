package com.monika.kotlinmvvmkoin.network

import com.monika.kotlinmvvmkoin.model.ApiResponse
import com.monika.kotlinmvvmkoin.model.Facts
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("facts.json")
    fun getFacts(): Call<ApiResponse<Facts>>
}