package com.huy.owasp2_mobile.improper_credentail_usage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credential")
data class Credential(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password: String
)