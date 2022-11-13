package me.gabriel.hearthstone.data.hearthstone.source.remote

import me.gabriel.hearthstone.data.HearthStoneApiService
import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneRemoteEntity
import javax.inject.Inject
import javax.inject.Singleton

interface HearthStoneRemoteDataSource {
    suspend fun returnHearthStoneList(): Map<String, List<HearthStoneRemoteEntity>>
}

@Singleton
class RealHearthStoneRemoteDataSource @Inject constructor(private val hearthStoneApiService: HearthStoneApiService) :
    HearthStoneRemoteDataSource {
    override suspend fun returnHearthStoneList() = hearthStoneApiService.returnCardsList()
}
