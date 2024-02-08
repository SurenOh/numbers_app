package com.example.myapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.ErrorMessage
import com.example.myapplication.model.NumberModel
import com.example.myapplication.repository.history.HistoryRepository
import com.example.myapplication.repository.number.NumberRepository
import com.example.myapplication.repository.number.NumberStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val numberRepository: NumberRepository,
    private val historyRepository: HistoryRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    sealed class UiState {
        data object EmptyState : UiState()
        class OnGetFact(val model: NumberModel) : UiState()
        class OnGetHistory(val list: ArrayList<NumberModel>) : UiState()
        class OnError(val error: ErrorMessage) : UiState()
    }

    fun clearState() {
        _uiState.postValue(UiState.EmptyState)
    }

    fun getFact(isInternetAvailable: Boolean, number: String? = null) {
        try {
            when {
                !isInternetAvailable -> _uiState.postValue(UiState.OnError(ErrorMessage.NoInternet))
                number == null -> numberRepository.getRandomNumberFact { checkNumberStatus(it) }
                else -> {
                    number.toLong()
                    numberRepository.getNumberFact(number) { checkNumberStatus(it) }
                }
            }
        } catch (e: NumberFormatException) {
            _uiState.postValue(UiState.OnError(ErrorMessage.IncorrectValue))
        } catch (e: Exception) {
            _uiState.postValue(UiState.OnError(ErrorMessage.Unknown))
        }
    }

    fun saveHistory(model: NumberModel) {
        viewModelScope.launch(Dispatchers.IO) {
            historyRepository.insertHistory(model)
        }
    }

    fun getHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val historyList = historyRepository.getHistory()
            _uiState.postValue(UiState.OnGetHistory(ArrayList(historyList)))
        }
    }

    private fun checkNumberStatus(status: NumberStatus) {
        val state = when (status) {
            is NumberStatus.StatusErrorHttp -> UiState.OnError(ErrorMessage.HttpError)
            is NumberStatus.StatusError -> UiState.OnError(ErrorMessage.ServerError)
            is NumberStatus.StatusSuccess -> UiState.OnGetFact(status.result)
        }
        _uiState.postValue(state)
    }
}