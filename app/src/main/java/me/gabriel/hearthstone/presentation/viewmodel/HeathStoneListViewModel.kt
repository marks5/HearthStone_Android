package me.gabriel.hearthstone.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.gabriel.hearthstone.domain.HearthStoneDomainModel
import me.gabriel.hearthstone.domain.HearthStoneListUseCase
import me.gabriel.hearthstone.extension.onSuccess
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HeathStoneListViewModel @Inject constructor(
    private val hearthStoneUseCase: HearthStoneListUseCase,
) : ViewModel() {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _cardList = MutableLiveData<List<HearthStoneDomainModel>>()
    val cardList: LiveData<List<HearthStoneDomainModel>> = _cardList

    private suspend fun fetchCardList() {
        hearthStoneUseCase.getCardList()
            .catch {
                _cardList.postValue(emptyList()).also {
                    Timber.e("HomeViewModel", "ON CALL ERROR -> $it")
                }
            }.onSuccess {
                _cardList.postValue(it)
            }
    }

    fun getCardList() {
        viewModelScope.launch {
            fetchCardList()
        }
    }

    fun doFetchCardList() {
        viewModelScope.launch(dispatcher) {
            hearthStoneUseCase.refreshCardList()
        }
    }

}
