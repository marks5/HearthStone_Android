package me.gabriel.hearthstone.data.hearthstone

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import me.gabriel.hearthstone.data.hearthstone.source.local.HearthStoneLocalDataSource
import me.gabriel.hearthstone.data.hearthstone.source.remote.HearthStoneRemoteDataSource
import me.gabriel.hearthstone.domain.HearthStoneDomainModel
import javax.inject.Inject
import javax.inject.Singleton

interface HearthStoneRepository {
    suspend fun refreshHearthStoneList()
    fun returnBiomarkerLocallyListAsFlow(): Flow<List<HearthStoneDomainModel>>
}

@Singleton
class RealHearthStoneRepository @Inject constructor(
    private val biomarkerRemoteDataSource: HearthStoneRemoteDataSource,
    private val biomarkerLocalDataSource: HearthStoneLocalDataSource,
) : HearthStoneRepository {

    override suspend fun refreshHearthStoneList() {
        biomarkerRemoteDataSource.returnBiomarkersList().mapNotNull { each ->
                each.value.map { it.mapperToDatabaseEntity() }.toTypedArray()
                    .let { biomarkerLocalDataSource.insertBiomarker(*it) }
            }
    }

    override fun returnBiomarkerLocallyListAsFlow() =
        biomarkerLocalDataSource.returnBiomarkersListAsFlow()
            .map { it.map { it.mapperToDomainModel() } }.flowOn(Dispatchers.IO)
}
