package com.huy.owasp2_mobile


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun InsecureAuthenticationApp() {
    InsecureClientAuthDemo(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
}

@Composable
fun InsecureClientAuthDemo(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    // KHÔNG AN TOÀN: Thông tin xác thực được hardcode trong app!
    val hardcodedUsername = "admin"
    val hardcodedPassword = "123456"

    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Insecure Authentication (Client-side)", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            // Xác thực hoàn toàn trên client, chỉ so sánh với giá trị cứng!
            if (username == hardcodedUsername && password == hardcodedPassword) {
                message = "Login successful! (INSECURE: Client-only authentication)"
            } else {
                message = "Invalid credentials!"
            }
        }) {
            Text("Login")
        }
        Spacer(Modifier.height(20.dp))
        Text(message)
    }
}