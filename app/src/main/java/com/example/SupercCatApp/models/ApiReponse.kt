package com.example.SupercCatApp.models

import com.google.gson.annotations.SerializedName

data class ApiReponse (
    @SerializedName("results")
    val results: List<Cat> //error
){
}