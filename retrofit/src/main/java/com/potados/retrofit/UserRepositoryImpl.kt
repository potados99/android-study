package com.potados.retrofit

import retrofit2.Callback

class UserRepositoryImpl(private val service: DummyDataService) :
    UserRepository {
    override fun getUsers(callback: Callback<List<User>>) {
        service.getUsers().enqueue(callback)
    }

    override fun getUserById(id: Int, callback: Callback<User>) {
        service.getUser(id).enqueue(callback)
    }
}