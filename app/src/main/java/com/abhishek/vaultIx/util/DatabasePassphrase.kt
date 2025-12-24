package com.abhishek.vaultIx.util

import androidx.security.crypto.MasterKey
import net.sqlcipher.database.SQLiteDatabase

object DatabasePassphrase {
    fun getPassPhrase(masterKey: MasterKey): ByteArray {
        return SQLiteDatabase.getBytes(masterKey.toString().toCharArray())
    }
}