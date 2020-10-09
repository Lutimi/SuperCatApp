package com.example.SupercCatApp.network

import com.example.SupercCatApp.models.ApiReponse
import com.example.SupercCatApp.models.Cat
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {
    @GET("images/search")
    fun getCats(
        @Query("limit")limit: String,
        @Query("page")page: String,
        @Query("order")order: String): Call<List<Cat>>

}