package com.beam.tictactoexml.di

import com.beam.tictactoexml.data.datasource.BoardDataSource
import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.data.datasource.GamesDataSource
import com.beam.tictactoexml.data.datasource.GamesLocalDataSource
import com.beam.tictactoexml.data.datasource.ScoreDataSource
import com.beam.tictactoexml.data.datasource.ScoreLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindBoardDataSource(impl: BoardDataSource): BoardLocalDataSource

    @Binds
    abstract fun bindScoreDataSource(impl: ScoreDataSource): ScoreLocalDataSource

    @Binds
    abstract fun bindGamesDataSource(impl: GamesDataSource): GamesLocalDataSource

}