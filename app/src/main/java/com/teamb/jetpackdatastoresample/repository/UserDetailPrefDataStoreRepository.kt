package com.teamb.jetpackdatastoresample.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.teamb.jetpackdatastoresample.common.Constants
import com.teamb.jetpackdatastoresample.presentation.UserFormState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserDetailPrefDataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun updateUserDetail(state: UserFormState) {
        dataStore.edit { settings ->
            settings[Constants.FIRST_NAME] = state.firstName
            settings[Constants.LAST_NAME] = state.lastName
            settings[Constants.PHONE_NUMBER] = state.phoneNumber
            settings[Constants.EMAIL] = state.email
        }
    }

    fun getUserDetailData(): Flow<UserFormState> = dataStore.data.map {
        UserFormState(
            firstName = it[Constants.FIRST_NAME].orEmpty(),
            lastName = it[Constants.LAST_NAME].orEmpty(),
            email = it[Constants.EMAIL].orEmpty(),
            phoneNumber = it[Constants.PHONE_NUMBER].orEmpty()
        )
    }
}