package me.gabriel.hearthstone.data.hearthstone.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneDatabaseEntity

@Dao
interface HearthStoneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCard(vararg biomarkerDatabaseEntity: HearthStoneDatabaseEntity)

    @Query(
        "SELECT * FROM hearthstone_table WHERE img not null and img != ''"
    ) fun returnCardsListAsFlow(): Flow<List<HearthStoneDatabaseEntity>>

}
