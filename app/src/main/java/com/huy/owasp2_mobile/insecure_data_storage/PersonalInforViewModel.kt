package com.huy.owasp2_mobile.insecure_data_storage


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PersonalInfoViewModel(private val dao: PersonalInfoDao) : ViewModel() {
    private val _latestInfo = MutableStateFlow<PersonalInfo?>(null)
    val latestInfo: StateFlow<PersonalInfo?> = _latestInfo

    fun save(name: String, address: String) {
        viewModelScope.launch {
            dao.insert(PersonalInfo(name = name, address = address))
            loadLatest()
        }
    }

    fun loadLatest() {
        viewModelScope.launch {
            _latestInfo.value = dao.getLatestInfo()
        }
    }
}