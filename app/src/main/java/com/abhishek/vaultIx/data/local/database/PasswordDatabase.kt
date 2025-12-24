package com.abhishek.vaultIx.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abhishek.vaultIx.data.local.dao.PasswordDao
import com.abhishek.vaultIx.data.local.entity.PasswordEntity

@Database(
    entities = [PasswordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PasswordDatabase : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao
}