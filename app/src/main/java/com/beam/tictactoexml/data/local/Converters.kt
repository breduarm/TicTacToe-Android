package com.beam.tictactoexml.data.local

import androidx.room.TypeConverter
import com.beam.tictactoexml.domain.O
import com.beam.tictactoexml.domain.Winner
import com.beam.tictactoexml.domain.X
import java.util.Date

class Converters {
    @TypeConverter
    fun fromWinner(value: Winner): String = value.toString()

    @TypeConverter
    fun toWinner(value: String): Winner = if (value == "X") X else O

    @TypeConverter
    fun fromDate(value: Date): Long = value.time

    @TypeConverter
    fun toDate(value: Long): Date = Date(value)
}