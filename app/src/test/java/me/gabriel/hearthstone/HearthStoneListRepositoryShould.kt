package me.gabriel.hearthstone

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import me.gabriel.hearthstone.data.hearthstone.RealHearthStoneRepository
import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneDatabaseEntity
import me.gabriel.hearthstone.data.hearthstone.entity.HearthStoneRemoteEntity
import me.gabriel.hearthstone.data.hearthstone.source.local.HearthStoneLocalDataSource
import me.gabriel.hearthstone.data.hearthstone.source.remote.HearthStoneRemoteDataSource
import me.gabriel.hearthstone.utils.BaseUnitTest
import org.junit.Test

class HearthStoneListRepositoryShould : BaseUnitTest() {

    private val localDataSource: HearthStoneLocalDataSource = mock()
    private val remoteDataSource: HearthStoneRemoteDataSource = mock()
    private val databaseEntity = mock<List<HearthStoneDatabaseEntity>>()
    private val remoteEntity = mock<Map<String, List<HearthStoneRemoteEntity>>>()

    @Test
    fun getHearthStoneListFromRemoteSource() = runBlockingTest {
        mockSuccessfulCase().apply {
            refreshHearthStoneList()
        }

        verify(remoteDataSource, times(1)).returnHearthStoneList()
    }

    @Test
    fun getHearthStoneListFromLocalSource() = runBlockingTest {
        mockSuccessfulCase().apply {
            returnHearthStoneListLocally()
        }

        verify(localDataSource, times(1)).returnListAsFlow()
    }

    private fun mockSuccessfulCase(): RealHearthStoneRepository {
        runBlocking {
            whenever(remoteDataSource.returnHearthStoneList())
                .thenReturn(remoteEntity)

            whenever(localDataSource.returnListAsFlow()).thenReturn(
                flow {
                    emit(databaseEntity)
                }
            )
        }

        return RealHearthStoneRepository(remoteDataSource, localDataSource)
    }

}