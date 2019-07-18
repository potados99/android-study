package com.potados.retrofit

import retrofit2.Callback

interface UserRepository {
    fun getUsers(callback: Callback<List<User>>)
    fun getUserById(id: Int, callback: Callback<User>)
}