package me.gabriel.hearthstone.domain

import kotlinx.coroutines.flow.Flow
import me.gabriel.hearthstone.data.hearthstone.HearthStoneRepository
import javax.inject.Inject

interface HearthStoneListUseCase {
    fun getCardList(): Flow<List<HearthStoneDomainModel>>
    suspend fun refreshCardList()
}

class RealHearthStoneListUseCase @Inject constructor(
    private val repository: HearthStoneRepository
) : HearthStoneListUseCase {
    override fun getCardList() =
        repository.returnHearthStoneListLocally()

    override suspend fun refreshCardList() = repository.refreshHearthStoneList()
}
