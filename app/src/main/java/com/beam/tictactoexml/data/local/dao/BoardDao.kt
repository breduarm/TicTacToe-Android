package com.beam.tictactoexml.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.beam.tictactoexml.data.local.entity.MoveEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BoardDao {

    @Query("SELECT * FROM MoveEntity")
    fun getBoard(): Flow<List<MoveEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMove(move: MoveEntity)

    @Query("DELETE FROM MoveEntity")
    suspend fun reset()
}