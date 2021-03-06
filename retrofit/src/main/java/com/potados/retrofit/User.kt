package com.potados.retrofit

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")       val id: Int,
    @SerializedName("name")     val name: String,
    @SerializedName("email")    val email: String,
    @SerializedName("phone")    val phone: String
) {
}