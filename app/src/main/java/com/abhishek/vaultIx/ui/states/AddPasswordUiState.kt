package com.abhishek.vaultIx.ui.states

data class AddPasswordUiState(
    val id: Int? = null,
    val accountName: String = "",
    val accountId: String = "",
    val password: String = "",
    val sheetVisible: Boolean = false
)