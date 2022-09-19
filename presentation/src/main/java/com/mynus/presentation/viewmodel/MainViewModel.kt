package com.mynus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mynus.datasource.mediator.DealsMediator
import com.mynus.domain.model.Deal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mediator: DealsMediator
): ViewModel() {

    private val _state = MutableSharedFlow<PagingData<Deal>>()
    val state = _state.asSharedFlow()

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    fun getDeals() {
        viewModelScope.launch {
            _loadingState.emit(true)

            mediator.getDeals().collect { pagingData ->
                _loadingState.emit(false)
                _state.emit(pagingData)
            }
        }
    }
}