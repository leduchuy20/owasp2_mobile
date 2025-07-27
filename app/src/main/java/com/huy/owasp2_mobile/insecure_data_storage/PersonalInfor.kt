package com.huy.owasp2_mobile.insecure_data_storage


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personal_info")
data class PersonalInfo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val address: String
)