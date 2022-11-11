package me.gabriel.hearthstone.data.hearthstone.source.local

import kotlinx.coroutines.flow.Flow
import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneDatabaseEntity
import javax.inject.Inject
import javax.inject.Singleton

interface HearthStoneLocalDataSource {
    fun insertBiomarker(vararg biomarkerDatabaseEntity: HearthStoneDatabaseEntity)
    fun returnBiomarkersListAsFlow(): Flow<List<HearthStoneDatabaseEntity>>
}

@Singleton
class RealHearthStoneLocalDataSource @Inject constructor(private val biomarkerDao: HearthStoneDao) :
    HearthStoneLocalDataSource {

    override fun insertBiomarker(vararg biomarkerDatabaseEntity: HearthStoneDatabaseEntity) =
        biomarkerDao.insertBiomarker(*biomarkerDatabaseEntity)

    override fun returnBiomarkersListAsFlow(): Flow<List<HearthStoneDatabaseEntity>> =
        biomarkerDao.returnBiomarkersListAsFlow()
}
