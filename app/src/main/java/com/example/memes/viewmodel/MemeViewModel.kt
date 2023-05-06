package com.example.memes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memes.api.RetrofitInstance
import com.example.memes.model.RandomMeme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MemeViewModel(): ViewModel() {

    private var _randomMemeLD = MutableLiveData<RandomMeme>()
    val randomMemeLD: LiveData<RandomMeme> = _randomMemeLD

    init {
        getRandomMeme()
    }

    fun getRandomMeme(){
        RetrofitInstance.api.getRandomMeme().enqueue(object : Callback<RandomMeme>{
            override fun onResponse(call: Call<RandomMeme>, response: Response<RandomMeme>) {
                if (response.body() != null){
                    _randomMemeLD.value = response.body()
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<RandomMeme>, t: Throwable) {
                Log.d("test", t.message.toString())
            }
        })
    }
}