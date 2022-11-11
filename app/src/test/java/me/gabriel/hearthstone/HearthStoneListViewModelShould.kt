package me.gabriel.hearthstone

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import me.gabriel.hearthstone.domain.HearthStoneDomainModel
import me.gabriel.hearthstone.domain.HearthStoneListUseCase
import me.gabriel.hearthstone.presentation.viewmodel.HeathStoneListViewModel
import me.gabriel.hearthstone.utils.BaseUnitTest
import org.junit.Assert.assertEquals
import org.junit.Test
import petros.efthymiou.groovy.utils.getValueForTest

class HearthStoneListViewModelShould : BaseUnitTest() {

    private val useCase: HearthStoneListUseCase = mock()
    private val models = mock<List<HearthStoneDomainModel>>()

    @Test
    fun getHearthStoneListFromUseCase() = runBlockingTest {
        mockSuccessfulCaseRemote().apply {
            doFetchCardList()
            cardList.getValueForTest()
        }

        verify(useCase, times(1)).refreshCardList()
    }

    @Test
    fun getHearthStoneListFromUseCaseLocally() = runBlockingTest {
        val viewModel = mockSuccessfulCase()

        viewModel.getCardList()
        viewModel.cardList.getValueForTest()

        verify(useCase, times(1)).getCardList()
    }

    @Test
    fun emitsHearthStoneFromUseCase() = runBlockingTest {
        val viewModel = mockSuccessfulCase()
        viewModel.getCardList()

        assertEquals(models, viewModel.cardList.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveErrorSuccess() = runBlockingTest {
        val viewModel = mockSuccessfulCaseRemote()
        viewModel.doFetchCardList()

        verify(useCase, times(1)).refreshCardList()
    }

    @Test
    fun emitErrorWhenReceiveError() = runBlockingTest {
        val viewModel = mockErrorCase()
        viewModel.getCardList()

        assertEquals(0, viewModel.cardList.getValueForTest()?.size)
    }


    private fun mockSuccessfulCase(): HeathStoneListViewModel {
        runBlocking {
            whenever(useCase.getCardList()).thenReturn(
                flow {
                    emit(models)
                }
            )
        }

        return HeathStoneListViewModel(useCase)
    }

    private fun mockSuccessfulCaseRemote(): HeathStoneListViewModel {
        runBlocking {
            whenever(useCase.refreshCardList()).thenReturn(Unit)
        }

        return HeathStoneListViewModel(useCase, Dispatchers.Unconfined)
    }

    private fun mockErrorCase(): HeathStoneListViewModel {
        runBlocking {
            whenever(useCase.getCardList()).thenReturn(
                flow {
                    emit(throw Exception())
                }
            )
        }
        return HeathStoneListViewModel(useCase)
    }

}