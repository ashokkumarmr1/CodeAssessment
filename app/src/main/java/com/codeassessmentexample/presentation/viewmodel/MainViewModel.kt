package com.codeassessmentexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeassessmentexample.common.core.Resource
import com.codeassessmentexample.common.di.MainDispatcher
import com.codeassessmentexample.domain.usecase.user.UserListUseCase
import com.codeassessmentexample.domain.usecase.user.UserSaveUseCase
import com.codeassessmentexample.presentation.state.UserListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    @MainDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val userListUseCase: UserListUseCase, private val userSaveUseCase: UserSaveUseCase
) : ViewModel()
{
    val _uiState = MutableStateFlow(UserListUiState())
    val uiState : StateFlow<UserListUiState> = _uiState

     init {
         getResult()
     }

    fun getResult() {

        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                userListUseCase.getUsers().onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _uiState.value = uiState.value.copy(
                                userList = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _uiState.value = uiState.value.copy(
                                userList = result.data ?: emptyList(),
                                isLoading = false
                            )
                            withContext(Dispatchers.IO){userSaveUseCase.saveUserList(result.data?: emptyList())}
                        }

                        is Resource.Error -> {
                            _uiState.value = uiState.value.copy(
                                userList = emptyList(),
                                isLoading = false,
                                error = "Something went wrong"
                            )
                        }

                        else -> {}
                    }
                }.launchIn(this)
            }
        }
    }

}