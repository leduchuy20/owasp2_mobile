package com.huy.owasp2_mobile
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*

@Composable
fun InsecureCommunicationApp() {
    InsecureCommunicationDemo(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
}

@Composable
fun InsecureCommunicationDemo(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Insecure Communication Demo", style = MaterialTheme.typography.titleLarge)
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
            scope.launch(Dispatchers.IO) {
                // LỖ HỔNG: Gửi thông tin nhạy cảm qua HTTP (không mã hóa)
                val client = OkHttpClient()
                val formBody = FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build()
                val request = Request.Builder()
                    .url("http://httpbin.org/post") // HTTP không bảo mật!
                    .post(formBody)
                    .build()
                try {
                    val response = client.newCall(request).execute()
                    val body = response.body?.string() ?: "No response"
                    message = "Response: $body\n(Sent over INSECURE HTTP!)"
                } catch (e: Exception) {
                    message = "Network error: ${e.message}"
                }
            }
        }) {
            Text("Send Credentials")
        }
        Spacer(Modifier.height(20.dp))
        Text(message)
    }
}