package com.beam.tictactoe.ui

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatToString(): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).run {
        format(this@formatToString)
    }