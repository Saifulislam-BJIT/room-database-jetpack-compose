package com.saiful.roomtest

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ClickDao {
    @Upsert
    suspend fun updateClick(click: Click)

    @Query("SELECT * FROM Click")
    fun getClick(): LiveData<List<Click>>
}