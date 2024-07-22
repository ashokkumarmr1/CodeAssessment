package com.codeassessmentexample.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeassessmentexample.common.Resource
import com.codeassessmentexample.di.MainDispatcher
import com.codeassessmentexample.domain.model.User
import com.codeassessmentexample.domain.usecase.UserUseCase
import com.codeassessmentexample.local.dao.UserDAO
import com.codeassessmentexample.local.model.UserEntity
import com.codeassessmentexample.remote.modelDTO.UserModel
import com.codeassessmentexample.ui.theme.state.UserListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel @Inject constructor (
    @MainDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val userUseCase : UserUseCase) : ViewModel()
{
    val _uiState = MutableStateFlow(UserListUiState())
    val uiState : StateFlow<UserListUiState> = _uiState

    var temp = null

    private  val lock = Mutex()

     init {
         getResult1()
     }

    fun getResult() {
        viewModelScope.launch()
        {
           async {
               userUseCase.getUsers().onEach { result ->
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
           }.await()

        }
    }

    fun getResult1() {

        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                userUseCase.getUsers().onEach { result ->
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
                            withContext(Dispatchers.IO){userUseCase.saveUsers(result.data?: emptyList())}
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