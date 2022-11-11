package me.gabriel.hearthstone.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneDatabaseEntity
import me.gabriel.hearthstone.data.hearthstone.source.local.HearthStoneDao

@Database(entities = [HearthStoneDatabaseEntity::class], version = 1)
abstract class HearthStoneRoomDatabase : RoomDatabase() {
    abstract fun biomarkerDao(): HearthStoneDao
}
