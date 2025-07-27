package com.huy.owasp2_mobile.insecure_data_storage


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PersonalInfo::class /*, Credential::class, ...*/], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personalInfoDao(): PersonalInfoDao
    // abstract fun credentialDao(): CredentialDao
}