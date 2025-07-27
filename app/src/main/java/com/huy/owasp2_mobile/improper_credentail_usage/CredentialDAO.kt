package com.huy.owasp2_mobile.improper_credentail_usage

import androidx.room.*

@Dao
interface CredentialDao {
    @Query("SELECT * FROM credential ORDER BY id DESC LIMIT 1")
    suspend fun getLatestCredential(): Credential?

    @Insert
    suspend fun insertCredential(credential: Credential)
}