package com.example.memes.api

import com.example.memes.model.RandomMeme
import retrofit2.Call
import retrofit2.http.GET

interface MemeApi {

    @GET("gimme")
    fun getRandomMeme(): Call<RandomMeme>
}