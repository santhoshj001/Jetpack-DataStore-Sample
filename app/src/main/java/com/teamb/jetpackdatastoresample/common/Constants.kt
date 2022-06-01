package com.teamb.jetpackdatastoresample.common

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val USER_PREFERENCE = "user_preference"
    const val USER_PREFERENCE_PROTO = "user_preference_proto"
    val FIRST_NAME = stringPreferencesKey("first_name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val PHONE_NUMBER = stringPreferencesKey("phone_number")
    val EMAIL = stringPreferencesKey("email")
}