package com.example.consumoapirickandmorty.model.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        fun retrofitInstance(): ApiRetrofit {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiRetrofit::class.java)

        }
    }
}