package com.teamb.jetpackdatastoresample.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.teamb.jetpackdatastoresample.UserDetail
import com.teamb.jetpackdatastoresample.presentation.UserFormState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserDetailProtoDataStoreRepository @Inject constructor(
    private val dataStore: DataStore<UserDetail>
) {


    val getUserFormState: Flow<UserFormState> = dataStore.data.map {
        UserFormState(
            firstName = it.firstName,
            lastName = it.lastName,
            email = it.email,
            phoneNumber = it.phoneNumber
        )
    }.catch { exception ->
        // dataStore.data throws an IOException when an error is encountered when reading data
        if (exception is IOException) {
            Log.e("proto ", "Error reading sort order preferences.", exception)
            emit(UserFormState())
        } else {
            throw exception
        }
    }


    suspend fun updateUserDetail(userFormState: UserFormState) {
        dataStore.updateData { current ->
            current.toBuilder()
                .setFirstName(userFormState.firstName)
                .setLastName(userFormState.lastName)
                .setEmail(userFormState.email)
                .setPhoneNumber(userFormState.phoneNumber)
                .build()
        }
    }

}