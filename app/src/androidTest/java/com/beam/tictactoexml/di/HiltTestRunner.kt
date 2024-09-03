package com.beam.tictactoexml.di

import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

@Suppress("unused")
class HiltTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        name: String?,
        context: android.content.Context?
    ): android.app.Application =
        super.newApplication(cl, HiltTestApplication::class.java.name, context)
}