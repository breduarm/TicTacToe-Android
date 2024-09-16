package com.beam.tictactoe.di

import android.app.Application
import androidx.room.Room
import com.beam.tictactoe.R
import com.beam.tictactoe.data.datasource.BoardLocalDataSource
import com.beam.tictactoe.data.datasource.BoardRoomDataSource
import com.beam.tictactoe.data.datasource.GamesRemoteDataSource
import com.beam.tictactoe.data.datasource.GamesRetrofitDataSource
import com.beam.tictactoe.data.datasource.ScoreLocalDataSource
import com.beam.tictactoe.data.datasource.ScoreRoomDataSource
import com.beam.tictactoe.data.local.AppDataBase
import com.beam.tictactoe.data.remote.GamesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideRemoteService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): GamesService =
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()

    @Provides
    @Singleton
    fun provideBoardDao(db: AppDataBase) = db.boardDao()

    @Provides
    @Singleton
    fun provideScoreDao(db: AppDataBase) = db.scoreDao()
}

@InstallIn(SingletonComponent::class)
@Module
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
    abstract fun bindBoardDataSource(impl: BoardRoomDataSource): BoardLocalDataSource

    @Binds
    abstract fun bindScoreDataSource(impl: ScoreRoomDataSource): ScoreLocalDataSource

    @Binds
    abstract fun bindRemoteGamesDataSource(impl: GamesRetrofitDataSource): GamesRemoteDataSource
}