package me.gabriel.hearthstone.data.hearthstone.source.remote

import me.gabriel.hearthstone.data.HearthStoneApiService
import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneRemoteEntity
import javax.inject.Inject
import javax.inject.Singleton

interface HearthStoneRemoteDataSource {
    suspend fun returnBiomarkersList(): Map<String, List<HearthStoneRemoteEntity>>
}

@Singleton
class RealHearthStoneRemoteDataSource @Inject constructor(private val biomarkersApiService: HearthStoneApiService) :
    HearthStoneRemoteDataSource {
    override suspend fun returnBiomarkersList() = biomarkersApiService.returnBiomarkersList()
}
