package com.example.memes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.memes.model.RandomMeme

@Dao
interface MemeDao {

    @Upsert
    suspend fun upsert(meme: RandomMeme)

    @Delete
    suspend fun delete(meme: RandomMeme)

    @Query("SELECT * FROM memeInfo")
    fun getAllMemes(): LiveData<List<RandomMeme>>
}