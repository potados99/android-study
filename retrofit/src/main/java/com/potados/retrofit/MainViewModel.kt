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
    val failure: MutableLiveData<String> = MutableLiveData()

    val cachedUsers: MutableList<User> = mutableListOf()

    fun loadTodos() = todoRepo.getTodos(object: Callback<List<Todo>> {
        override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
            if (!response.isSuccessful) return

            todos.value = response.body()?.shuffled()?.subList(0, 15)

            // loadUsers()
            fillName()
        }

        override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
            failure.value = t.message
        }
    })

    private fun fillName() {
        /*
        todos.value?.forEachIndexed { index, todo ->
            todo.userName = users.value?.find { it.id == todo.userId }?.name ?: "-"
        }
        todos.value = todos.value
        */

        todos.value?.forEach {
            val cachedName = cachedUsers.find { user -> user.id == it.userId }?.name

            if (cachedName != null) {
                it.userName = cachedName

                todos.value = todos.value
            }
            else {
                userRepo.getUserById(it.userId, object: Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (!response.isSuccessful) return

                        val fetchedName = response.body()?.name

                        it.userName = fetchedName ?: "-"
                        cachedUsers.add(response.body() ?: return)

                        todos.value = todos.value
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        it.userName = "-"
                    }
                })
            }
        }
    }

}