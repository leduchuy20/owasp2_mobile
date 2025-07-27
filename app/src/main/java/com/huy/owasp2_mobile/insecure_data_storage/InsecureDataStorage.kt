package com.huy.owasp2_mobile.insecure_data_storage

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun InsecureDataStorageApp() {
    InsecureDataStorageDemo(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    )
}

@Composable
fun InsecureDataStorageDemo(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current

    val dao = remember { DatabaseProvider.getDatabase(context).personalInfoDao() }
    val viewModel = remember { PersonalInfoViewModel(dao) }
    val latestInfo by viewModel.latestInfo.collectAsState()

    Column(
        modifier
            .fillMaxSize()
            .padding(start = 17.dp, end = 17.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Insecure Data Storage Demo", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            viewModel.save(name, address)
            message = "Personal info saved to RoomDB!"
        }) {
            Text("Save Personal Info")
        }
        Spacer(Modifier.height(10.dp))
        Button(onClick = {
            viewModel.loadLatest()
            val info = latestInfo
            if (info != null) {
                message = "Saved Name: ${info.name}\nSaved Address: ${info.address}"
            } else {
                message = "No personal info saved yet."
            }
        }) {
            Text("Show Saved Info")
        }
        Spacer(Modifier.height(20.dp))
        Text(message)
    }
}