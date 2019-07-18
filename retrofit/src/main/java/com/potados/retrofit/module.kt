package com.potados.retrofit

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val modules = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DummyDataService::class.java)
    }

    single {
        TodoRepositoryImpl(get()) as TodoRepository
    }

    single {
        UserRepositoryImpl(get()) as UserRepository
    }

}