package com.saiful.roomtest

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Click(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val click: Int
)