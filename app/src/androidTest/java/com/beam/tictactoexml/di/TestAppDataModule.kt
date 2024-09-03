package com.beam.tictactoexml.di

import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.data.datasource.BoardLocalDataSourceFake
import com.beam.tictactoexml.data.datasource.GamesRemoteDataSource
import com.beam.tictactoexml.data.datasource.GamesRemoteDataSourceFake
import com.beam.tictactoexml.data.datasource.ScoreLocalDataSource
import com.beam.tictactoexml.data.datasource.ScoreLocalDataSourceFake
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppDataModule::class],
)
@Module
class TestAppDataModule {

    @Provides
    @Singleton
    fun provideGamesRemoteDataSourceFake() = GamesRemoteDataSourceFake()

    @Provides
    @Singleton
    fun provideBoardLocalDataSourceFake() = BoardLocalDataSourceFake()

    @Provides
    @Singleton
    fun provideScoreLocalDataSourceFake() = ScoreLocalDataSourceFake()

    @Provides
    @Singleton
    fun provideGamesRemoteDataSource(fake: GamesRemoteDataSourceFake): GamesRemoteDataSource = fake

    @Provides
    @Singleton
    fun provideBoardLocalDataSource(fake: BoardLocalDataSourceFake): BoardLocalDataSource = fake

    @Provides
    @Singleton
    fun provideScoreLocalDataSource(fake: ScoreLocalDataSourceFake): ScoreLocalDataSource = fake
}
