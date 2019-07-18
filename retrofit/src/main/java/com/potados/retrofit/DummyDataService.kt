package com.potados.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DummyDataService {
    @GET("todos")
    fun getTodos(): Call<List<Todo>>

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{userId}")
    fun getUser(@Path("userId") id: String): Call<User>
}
