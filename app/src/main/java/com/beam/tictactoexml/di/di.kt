package com.beam.tictactoexml.di

import android.app.Application
import androidx.room.Room
import com.beam.tictactoexml.R
import com.beam.tictactoexml.data.datasource.BoardDataSource
import com.beam.tictactoexml.data.datasource.BoardLocalDataSource
import com.beam.tictactoexml.data.datasource.GamesRemoteDataSource
import com.beam.tictactoexml.data.datasource.GamesRetrofitDataSource
import com.beam.tictactoexml.data.datasource.ScoreDataSource
import com.beam.tictactoexml.data.datasource.ScoreLocalDataSource
import com.beam.tictactoexml.data.local.AppDataBase
import com.beam.tictactoexml.data.remote.GamesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String): GamesService =
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun provideBoardDao(db: AppDataBase) = db.boardDao()
}

@Module
@InstallIn(SingletonComponent::class)
object AppExtrasModule {

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = "https://api.rawg.io/api/"

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        context = app,
        klass = AppDataBase::class.java,
        name = "tic-tac-toe-db",
    ).build()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {

    @Binds
    abstract fun bindBoardDataSource(impl: BoardDataSource): BoardLocalDataSource

    @Binds
    abstract fun bindScoreDataSource(impl: ScoreDataSource): ScoreLocalDataSource

    @Binds
    abstract fun bindRemoteGamesDataSource(impl: GamesRetrofitDataSource): GamesRemoteDataSource
}