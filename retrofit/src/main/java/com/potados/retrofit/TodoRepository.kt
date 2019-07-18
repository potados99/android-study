package com.potados.retrofit

import retrofit2.Callback

interface TodoRepository {
    fun getTodos(callback: Callback<List<Todo>>)
}