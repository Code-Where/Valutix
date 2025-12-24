package com.abhishek.vaultIx.domain

import com.abhishek.vaultIx.data.local.entity.PasswordEntity
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {

    fun getAllPasswords(): Flow<List<PasswordEntity>>

    suspend fun getPasswordById(id: Int): PasswordEntity

    suspend fun insertPassword(passwordEntity: PasswordEntity)

    suspend fun deletePassword(passwordEntity: PasswordEntity)

    suspend fun deletePasswordById(id: Int)

    suspend fun updatePassword(passwordEntity: PasswordEntity)

}