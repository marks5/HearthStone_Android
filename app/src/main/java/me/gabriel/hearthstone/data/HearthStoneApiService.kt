package me.gabriel.hearthstone.data

import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneRemoteEntity
import retrofit2.http.GET

interface HearthStoneApiService {

    @GET("cards")
    suspend fun returnBiomarkersList(): Map<String, List<HearthStoneRemoteEntity>>

}
