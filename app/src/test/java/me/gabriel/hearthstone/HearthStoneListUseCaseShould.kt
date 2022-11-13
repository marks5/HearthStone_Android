package me.gabriel.hearthstone

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import me.gabriel.hearthstone.data.hearthstone.HearthStoneRepository
import me.gabriel.hearthstone.domain.HearthStoneDomainModel
import me.gabriel.hearthstone.domain.HearthStoneListUseCase
import me.gabriel.hearthstone.domain.RealHearthStoneListUseCase
import me.gabriel.hearthstone.utils.BaseUnitTest
import org.junit.Test

class HearthStoneListUseCaseShould : BaseUnitTest() {

    private val repo: HearthStoneRepository = mock()
    private val models = mock<List<HearthStoneDomainModel>>()

    @Test
    fun getHearthStoneListFromUseCase() = runBlockingTest {
        mockSuccessfulCaseRemote().apply {
            refreshCardList()
        }

        verify(repo, times(1)).refreshHearthStoneList()
    }

    @Test
    fun getHearthStoneListFromUseCaseLocally() = runBlockingTest {
        mockSuccessfulCase().apply {
            getCardList()
        }

        verify(repo, times(1)).returnHearthStoneListLocally()
    }

    @Test
    fun emitErrorWhenReceiveErrorSuccess() = runBlockingTest {
        val success = mockSuccessfulCaseRemote()
        success.refreshCardList()

        verify(repo, times(1)).refreshHearthStoneList()
    }

    @Test
    fun emitErrorWhenReceiveError() = runBlockingTest {
        mockErrorCase().apply {
            refreshCardList()
        }
        verify(repo, times(1)).refreshHearthStoneList()
    }


    private fun mockSuccessfulCase(): RealHearthStoneListUseCase {
        runBlocking {
            whenever(repo.returnHearthStoneListLocally()).thenReturn(
                flow {
                    emit(models)
                }
            )
        }

        return RealHearthStoneListUseCase(repo)
    }

    private fun mockSuccessfulCaseRemote(): HearthStoneListUseCase {
        runBlocking {
            whenever(repo.refreshHearthStoneList()).thenReturn(Unit)
        }

        return RealHearthStoneListUseCase(repo)
    }

    private fun mockErrorCase(): RealHearthStoneListUseCase {
        runBlocking {
            whenever(repo.returnHearthStoneListLocally()).thenReturn(
                flow {
                    emit(throw Exception())
                }
            )
        }
        return RealHearthStoneListUseCase(repo)
    }

}