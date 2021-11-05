package com.example.consumoapirickandmorty.model.retrofit

import com.example.consumoapirickandmorty.model.dataClass.RickandMorty
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiRetrofit {

    @GET("character")
    fun getAllcharacterFromApi(@Query("page")page:Int):Call<RickandMorty>
}