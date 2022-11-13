package me.gabriel.hearthstone.data.hearthstone.source.local

import kotlinx.coroutines.flow.Flow
import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneDatabaseEntity
import javax.inject.Inject
import javax.inject.Singleton

interface HearthStoneLocalDataSource {
    fun insertCard(vararg hearthStoneCardDatabaseEntity: HearthStoneDatabaseEntity)
    fun returnListAsFlow(): Flow<List<HearthStoneDatabaseEntity>>
}

@Singleton
class RealHearthStoneLocalDataSource @Inject constructor(private val hearthStoneDao: HearthStoneDao) :
    HearthStoneLocalDataSource {

    override fun insertCard(vararg hearthStoneCardDatabaseEntity: HearthStoneDatabaseEntity) =
        hearthStoneDao.insertCard(*hearthStoneCardDatabaseEntity)

    override fun returnListAsFlow(): Flow<List<HearthStoneDatabaseEntity>> =
        hearthStoneDao.returnCardsListAsFlow()
}
