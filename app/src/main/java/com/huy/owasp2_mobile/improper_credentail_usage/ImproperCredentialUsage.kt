package com.huy.owasp2_mobile.improper_credentail_usage

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext

@Composable
fun ImproperCredentialUsageApp(){
    ImproperCredentialUsageDemo(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
}
@Composable
fun ImproperCredentialUsageDemo(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val context = LocalContext.current
    val dao = remember { DatabaseProvider.getDatabase(context).credentialDao() }
    val viewModel = remember { CredentialViewModel(dao) }
    val latestCredential by viewModel.latestCredential.collectAsState()

    Column(
        modifier
            .fillMaxSize()
            .padding(17.dp)
    ) {
        Text("Improper Credential Usage Demo", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            viewModel.saveCredential(username, password)
            message = "Credentials saved to RoomDB!"
        }) {
            Text("Save Credentials")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = {
            viewModel.loadLatestCredential()
            val cred = latestCredential
            if (cred != null) {
                message = "Saved Username: ${cred.username}\nSaved Password: ${cred.password}"
            } else {
                message = "No credentials saved yet."
            }
        }) {
            Text("Show Saved Credentials")
        }
        Spacer(Modifier.height(20.dp))
        Text(message)
    }
}