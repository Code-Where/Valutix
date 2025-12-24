package com.abhishek.vaultIx.ui.states

import com.abhishek.vaultIx.data.local.entity.PasswordEntity

data class HomeUiState(
    val passwordList: List<PasswordEntity> = emptyList(),
    val selectedPassword: PasswordEntity? = null,
    val processState: HomeProcessState = HomeProcessState.Idle
)

sealed class HomeProcessState(){
    object Idle: HomeProcessState()
    data class Success(val passwordList: List<PasswordEntity>): HomeProcessState()
    data class Error(val message: String): HomeProcessState()
}