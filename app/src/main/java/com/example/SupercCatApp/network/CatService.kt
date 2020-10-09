package com.example.SupercCatApp.network

import com.example.SupercCatApp.models.ApiReponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {
    @GET("movie?")
    fun getCats(@Query("api_key")key: String, @Query("query")query: String): Call<ApiReponse>

}