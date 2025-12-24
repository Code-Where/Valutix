package com.abhishek.vaultIx.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abhishek.vaultIx.data.local.entity.PasswordEntity
import com.abhishek.vaultIx.util.RoomConstants
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {

    @Query("SELECT * FROM ${RoomConstants.passwordTable}")
    fun getAllPasswords() : Flow<List<PasswordEntity>>

    @Query("SELECT * FROM ${RoomConstants.passwordTable} WHERE id = :id")
    suspend fun getPasswordById(id: Int) : PasswordEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPassword(passwordEntity: PasswordEntity)

    @Delete
    suspend fun deletePassword(passwordEntity: PasswordEntity)

    @Query("DELETE FROM ${RoomConstants.passwordTable} WHERE id = :id")
    suspend fun deletePasswordById(id: Int)

    @Update
    suspend fun updatePassword(passwordEntity: PasswordEntity)
}