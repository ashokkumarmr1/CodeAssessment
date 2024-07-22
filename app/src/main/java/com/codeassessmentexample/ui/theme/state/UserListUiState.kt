package com.codeassessmentexample.ui.theme.state

import com.codeassessmentexample.domain.model.User

data class
UserListUiState(val userList : List<User> = emptyList(), val isLoading : Boolean = false, val error : String ="")
