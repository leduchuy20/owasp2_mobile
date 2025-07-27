package com.huy.owasp2_mobile


import android.content.Context
import android.os.Bundle
import android.util.Base64
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun InsufficientCryptographyApp() {
    InsufficientCryptoDemo(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
}

@Composable
fun InsufficientCryptoDemo(modifier: Modifier= Modifier) {
    var secret by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Insufficient Cryptography Demo", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = secret,
            onValueChange = { secret = it },
            label = { Text("Enter Secret Data") }
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            // LƯU Ý: KHÔNG AN TOÀN! Chỉ dùng Base64 (không phải mã hóa thực sự)
            val encoded = Base64.encodeToString(secret.toByteArray(), Base64.DEFAULT)
            val prefs = context.getSharedPreferences("crypto_prefs", Context.MODE_PRIVATE)
            prefs.edit().putString("encoded_secret", encoded).apply()
            message = "Secret encoded with Base64 and saved!\n(Base64 is NOT secure encryption!)"
        }) {
            Text("Save 'Encrypted' Secret")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = {
            val prefs = context.getSharedPreferences("crypto_prefs", Context.MODE_PRIVATE)
            val encoded = prefs.getString("encoded_secret", null)
            val decoded = if (encoded != null) String(Base64.decode(encoded, Base64.DEFAULT)) else "No data"
            message = "Stored (Base64): $encoded\nDecoded: $decoded"
        }) {
            Text("Show Stored Secret")
        }
        Spacer(Modifier.height(20.dp))
        Text(message)
    }
}