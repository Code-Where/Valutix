package com.abhishek.vaultIx.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhishek.vaultIx.util.RoomConstants

@Entity(tableName = RoomConstants.passwordTable)
data class PasswordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val accountName: String,
    val accountId: String,
    val accountPassword: String
)
