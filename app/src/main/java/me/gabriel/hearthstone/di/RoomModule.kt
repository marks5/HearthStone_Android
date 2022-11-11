package me.gabriel.hearthstone.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.gabriel.hearthstone.data.HearthStoneRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideBiomarkersRoomDatabase(application: Application): HearthStoneRoomDatabase {
        return Room.databaseBuilder(
            application,
            HearthStoneRoomDatabase::class.java,
            "hearthstone_table"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideBiomarkerDao(biomarkersRoomDatabase: HearthStoneRoomDatabase) =
        biomarkersRoomDatabase.biomarkerDao()

}
