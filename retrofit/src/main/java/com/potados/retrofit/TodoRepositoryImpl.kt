package com.potados.retrofit

import retrofit2.Callback

class TodoRepositoryImpl(private val service: DummyDataService) : TodoRepository {

    override fun getTodos(callback: Callback<List<Todo>>) {
        service.getTodos().enqueue(callback)
    }

}
