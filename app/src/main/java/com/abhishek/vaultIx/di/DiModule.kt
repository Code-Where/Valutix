package com.abhishek.vaultIx.di

import android.content.Context
import androidx.room.Room
import androidx.security.crypto.MasterKey
import com.abhishek.vaultIx.data.local.dao.PasswordDao
import com.abhishek.vaultIx.data.local.database.PasswordDatabase
import com.abhishek.vaultIx.data.repository.PasswordRepositoryImpl
import com.abhishek.vaultIx.domain.PasswordRepository
import com.abhishek.vaultIx.util.DatabasePassphrase
import com.abhishek.vaultIx.util.RoomConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import net.sqlcipher.database.SupportFactory

@Module
@InstallIn(SingletonComponent::class)
object PasswordModule {

    @Suppress("DEPRECATION")
    @Provides
    @Singleton
    fun getMasterKey(@ApplicationContext context: Context): MasterKey {
        return MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    @Provides
    @Singleton
    fun providePasswordDatabase(
        @ApplicationContext context: Context,
        masterKey: MasterKey
    ): PasswordDatabase
    {
        val passphrase = DatabasePassphrase.getPassPhrase(masterKey)
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(
            context,
            PasswordDatabase::class.java,
            RoomConstants.passwordTable
        )
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun providePasswordDao(
        passwordDatabase: PasswordDatabase
    ): PasswordDao = passwordDatabase.passwordDao()

    @Provides
    @Singleton
    fun providePasswordRepository(
        passwordDao: PasswordDao
    ): PasswordRepository = PasswordRepositoryImpl(passwordDao)

}