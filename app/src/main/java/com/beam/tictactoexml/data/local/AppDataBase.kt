package com.beam.tictactoexml.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beam.tictactoexml.data.local.dao.BoardDao
import com.beam.tictactoexml.data.local.entity.MoveEntity

@Database(entities = [MoveEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun boardDao(): BoardDao
}