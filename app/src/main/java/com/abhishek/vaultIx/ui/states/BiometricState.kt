package com.abhishek.vaultIx.ui.states

sealed class BiometricState {
    object Locked : BiometricState()
    object Authenticated : BiometricState()
    data class Error(val message: String) : BiometricState()
}