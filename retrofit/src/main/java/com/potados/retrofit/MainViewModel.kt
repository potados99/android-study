package com.potados.retrofit

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(), KoinComponent {

    private val todoRepo: TodoRepository by inject()
    private val userRepo: UserRepository by inject()

    val todos: MutableLiveData<List<Todo>> = MutableLiveData()
    val users: MutableLiveData<List<User>> = MutableLiveData()
    val failure: MutableLiveData<String> = MutableLiveData()

    fun loadTodos() = todoRepo.getTodos(object: Callback<List<Todo>> {
        override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
            if (!response.isSuccessful) return

            todos.value = response.body()?.shuffled()
            fillName()

            loadUsers()
        }

        override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
            failure.value = t.message
        }
    })

    fun loadUsers() = userRepo.getUsers(object: Callback<List<User>> {
        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
            if (!response.isSuccessful) return

            users.value = response.body()
            fillName()
        }

        override fun onFailure(call: Call<List<User>>, t: Throwable) {
            failure.value = t.message
        }
    })

    private fun fillName() {
        todos.value?.forEachIndexed { index, todo ->
            todo.userName = users.value?.find { it.id == todo.userId }?.name ?: "-"
        }
        todos.value = todos.value
    }

}