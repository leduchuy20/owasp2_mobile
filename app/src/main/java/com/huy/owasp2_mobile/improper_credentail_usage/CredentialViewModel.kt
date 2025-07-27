package com.huy.owasp2_mobile.improper_credentail_usage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CredentialViewModel(private val dao: CredentialDao) : ViewModel() {
    private val _latestCredential = MutableStateFlow<Credential?>(null)
    val latestCredential: StateFlow<Credential?> = _latestCredential

    fun saveCredential(username: String, password: String) {
        viewModelScope.launch {
            dao.insertCredential(Credential(username = username, password = password))
            loadLatestCredential()
        }
    }

    fun loadLatestCredential() {
        viewModelScope.launch {
            _latestCredential.value = dao.getLatestCredential()
        }
    }
}