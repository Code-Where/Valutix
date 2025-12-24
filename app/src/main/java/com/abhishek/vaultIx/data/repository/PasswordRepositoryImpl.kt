package com.abhishek.vaultIx.data.repository

import com.abhishek.vaultIx.data.local.dao.PasswordDao
import com.abhishek.vaultIx.data.local.entity.PasswordEntity
import com.abhishek.vaultIx.domain.PasswordRepository
import kotlinx.coroutines.flow.Flow

class PasswordRepositoryImpl(
    private val passwordDao: PasswordDao
): PasswordRepository {
    override fun getAllPasswords(): Flow<List<PasswordEntity>> {
        return passwordDao.getAllPasswords()
    }

    override suspend fun getPasswordById(id: Int): PasswordEntity {
        return passwordDao.getPasswordById(id)
    }

    override suspend fun insertPassword(passwordEntity: PasswordEntity) {
        passwordDao.insertPassword(passwordEntity)
    }

    override suspend fun deletePassword(passwordEntity: PasswordEntity) {
        passwordDao.deletePassword(passwordEntity)
    }

    override suspend fun deletePasswordById(id: Int) {
        passwordDao.deletePasswordById(id)
    }

    override suspend fun updatePassword(passwordEntity: PasswordEntity) {
        passwordDao.updatePassword(passwordEntity)
    }

}