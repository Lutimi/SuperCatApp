package com.example.SupercCatApp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "cats"
)
data class Cat (
    @PrimaryKey
    @SerializedName("id")
    val id : String,
    @SerializedName("url")
    val url : String,
    @SerializedName("width")
    val width: String,
    @SerializedName("height")
    val height: String

) : Serializable{
}