package com.beam.tictactoexml.di

import android.app.Application
import androidx.room.Room
import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.data.datasource.BoardLocalDataSourceFake
import com.beam.tictactoexml.data.datasource.BoardRoomDataSource
import com.beam.tictactoexml.data.datasource.GamesRemoteDataSource
import com.beam.tictactoexml.data.datasource.GamesRemoteDataSourceFake
import com.beam.tictactoexml.data.datasource.ScoreLocalDataSource
import com.beam.tictactoexml.data.datasource.ScoreLocalDataSourceFake
import com.beam.tictactoexml.data.datasource.ScoreRoomDataSource
import com.beam.tictactoexml.data.local.AppDataBase
import com.beam.tictactoexml.data.local.dao.BoardDao
import com.beam.tictactoexml.data.local.dao.ScoreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asExecutor
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppDataModule::class, AppExtrasModule::class],
)
@Module
class TestAppDataModule {

    @Provides
    @Singleton
    fun provideDataBase(app: Application) = Room.inMemoryDatabaseBuilder(
        app,
        AppDataBase::class.java,
    )
        .setTransactionExecutor(Dispatchers.Main.asExecutor())
        .allowMainThreadQueries()
        .build()

    @Provides
    fun provideBoardDataSource(boardDao: BoardDao): BoardLocalDataSource =
        BoardRoomDataSource(boardDao)

    @Provides
    fun provideScoreDataSource(scoreDao: ScoreDao): ScoreLocalDataSource =
        ScoreRoomDataSource(scoreDao)

    @Provides
    @Singleton
    fun provideGamesRemoteDataSourceFake() = GamesRemoteDataSourceFake()

    @Provides
    @Singleton
    fun provideGamesRemoteDataSource(fake: GamesRemoteDataSourceFake): GamesRemoteDataSource = fake
}
