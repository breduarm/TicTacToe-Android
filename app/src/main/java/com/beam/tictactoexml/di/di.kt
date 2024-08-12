package com.beam.tictactoexml.di

import com.beam.tictactoexml.data.datasource.BoardDataSource
import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AppDataModule {

    @Binds
    abstract fun bindBoardDataSource(impl: BoardDataSource): BoardLocalDataSource
}