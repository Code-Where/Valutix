package com.abhishek.vaultIx.util

sealed class BiometricStatus {
    object Available : BiometricStatus()
    object NotEnrolled : BiometricStatus()
    object NotSupported : BiometricStatus()
}