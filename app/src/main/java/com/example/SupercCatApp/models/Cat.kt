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
    val id : Int = 0,
    @SerializedName("name")
    val title : String,
    @SerializedName("overview")
    val overview: String

) : Serializable{
}