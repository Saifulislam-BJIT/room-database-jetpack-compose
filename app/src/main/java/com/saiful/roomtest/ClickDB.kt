package com.saiful.roomtest

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Click::class],
    version = 1
)
abstract class ClickDB : RoomDatabase() {
    abstract val dao: ClickDao
}
