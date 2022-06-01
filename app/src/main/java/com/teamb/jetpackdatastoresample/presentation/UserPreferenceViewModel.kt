package com.teamb.jetpackdatastoresample.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamb.jetpackdatastoresample.repository.UserDetailPrefDataStoreRepository
import com.teamb.jetpackdatastoresample.repository.UserDetailProtoDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPreferenceViewModel @Inject constructor(
    private val userPreferencesRepository: UserDetailPrefDataStoreRepository,
    private val userDetailProtoDataStoreRepository: UserDetailProtoDataStoreRepository
) : ViewModel() {
    var state by mutableStateOf(UserFormState())
        private set

    private val IS_PROTO = true


    init {
        viewModelScope.launch {
            if (IS_PROTO) {
                userDetailProtoDataStoreRepository.getUserFormState.collect {
                    state = it
                }
            } else {
                userPreferencesRepository.getUserDetailData().collect { newState ->
                    state = newState
                }
            }
        }
    }

    fun onEvent(event: UserFormEvent) {
        when (event) {
            is UserFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is UserFormEvent.FirstNameChanged -> {
                state = state.copy(firstName = event.firstName)
            }
            is UserFormEvent.LastNameChanged -> {
                state = state.copy(lastName = event.lastName)
            }
            is UserFormEvent.PhoneNumberChanged -> {
                state = state.copy(phoneNumber = event.phoneNumber)
            }
            UserFormEvent.Submit -> {
                saveData()
            }
        }
    }

    private fun saveData() {
        viewModelScope.launch {
            if (IS_PROTO) {
                userDetailProtoDataStoreRepository.updateUserDetail(state)
            } else {
                userPreferencesRepository.updateUserDetail(state)
            }
        }
    }
}