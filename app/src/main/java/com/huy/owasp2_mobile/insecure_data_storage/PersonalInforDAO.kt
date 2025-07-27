package com.huy.owasp2_mobile.insecure_data_storage


import androidx.room.*

@Dao
interface PersonalInfoDao {
    @Insert
    suspend fun insert(personalInfo: PersonalInfo)

    @Query("SELECT * FROM personal_info ORDER BY id DESC LIMIT 1")
    suspend fun getLatestInfo(): PersonalInfo?
}