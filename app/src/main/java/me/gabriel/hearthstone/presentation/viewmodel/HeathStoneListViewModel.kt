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
import javax.inject.Inject

@HiltViewModel
class HeathStoneListViewModel @Inject constructor(
    private val hearthStoneUseCase: HearthStoneListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _cardList = MutableLiveData<List<HearthStoneDomainModel>>()
    val cardList: LiveData<List<HearthStoneDomainModel>> = _cardList

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    private suspend fun fetchCardList() {
        hearthStoneUseCase.getCardList()
            .catch {
                _cardList.postValue(emptyList())
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
