package com.abhishek.vaultIx.util

import com.abhishek.vaultIx.data.local.entity.PasswordEntity

val samplePasswords = listOf(
    PasswordEntity(
        id = 1,
        accountName = "Instagram",
        accountId = "abhishek_ig",
        accountPassword = "insta@123"
    ),
    PasswordEntity(
        id = 2,
        accountName = "Google",
        accountId = "abhishek.dhawan@gmail.com",
        accountPassword = "google@456"
    ),
    PasswordEntity(
        id = 3,
        accountName = "GitHub",
        accountId = "abhishek-dev",
        accountPassword = "git@789"
    )
)
