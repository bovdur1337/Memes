package com.example.memes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memeInfo")
data class RandomMeme(
    @PrimaryKey
    val postLink: String,
    val url: String
)