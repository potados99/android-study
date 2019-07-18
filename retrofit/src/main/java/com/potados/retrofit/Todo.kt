package com.potados.retrofit

import com.google.gson.annotations.SerializedName

data class Todo(
    @SerializedName("userId")       val userId: Int,
    @SerializedName("todoId")       val id: Int,
    @SerializedName("title")        val title: String,
    @SerializedName("completed")    val done: Boolean,
    var userName: String
) {
}