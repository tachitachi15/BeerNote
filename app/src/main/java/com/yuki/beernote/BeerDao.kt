package com.yuki.beernote

import androidx.room.*

@Dao
interface BeerDao {
    @Query("SELECT * FROM beer")
    fun getAll(): List<Beer>

    @Insert
    fun insert(beer:Beer)

    @Update
    fun update(beer:Beer)

    @Delete
    fun delete(beer:Beer)
}