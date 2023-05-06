package com.example.memes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.memes.model.RandomMeme

@Database(
    entities = [RandomMeme::class],
    version = 1
)
abstract class MemeDatabase: RoomDatabase() {

    abstract fun memeDao(): MemeDao

    companion object{
        @Volatile
        var INSTANCE: MemeDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MemeDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MemeDatabase::class.java,
                    "meme_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MemeDatabase
        }
    }
}