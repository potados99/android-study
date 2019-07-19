package com.potados.retrofit

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 메인 액티비티에서 사용할 뷰모델
 */
class MainViewModel : ViewModel(), KoinComponent {

    private val todoRepo: TodoRepository by inject()
    private val userRepo: UserRepository by inject()

    /**
     * 액티비티에서 구독할 To-do 리스트
     */
    val todos: MutableLiveData<List<Todo>> = MutableLiveData()

    /**
     * 액티비티에서 구독할 실패 정보
     */
    val failure: MutableLiveData<String> = MutableLiveData()

    /**
     * User 객체 중 네트워크에서 가져온 것을 저장
     */
    private val cachedUsers: MutableList<User> = mutableListOf()

    /**
     * todos를 업데이트
     */
    fun loadTodos() = todoRepo.getTodos(object: Callback<List<Todo>> {
        override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
            if (!response.isSuccessful) return

            todos.value = response.body()?.shuffled()?.subList(0, 15)

            fillName()
        }

        override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
            failure.value = t.message
        }
    })

    /**
     * todos의 각 아이템에 userName 속성 채우
     */
    private fun fillName() {

        /**
         * todos의 모든 아이템을 순회하며 userName 채우기
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