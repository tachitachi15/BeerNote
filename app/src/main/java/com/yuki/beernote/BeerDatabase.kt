package com.yuki.beernote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Beer::class],version=1)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao
    companion object{
        private var INSTANCE: BeerDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): BeerDatabase {
            synchronized(lock){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        BeerDatabase::class.java, "Beer.db")
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE!!
            }
        }
    }
}