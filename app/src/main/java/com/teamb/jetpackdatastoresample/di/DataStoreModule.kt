package com.teamb.jetpackdatastoresample.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.teamb.jetpackdatastoresample.UserDetail
import com.teamb.jetpackdatastoresample.common.Constants
import com.teamb.jetpackdatastoresample.data.UserDetailSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataStoreModule {

    @Singleton
    @Provides
    fun providesPreferenceDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { context.preferencesDataStoreFile(Constants.USER_PREFERENCE) }
        )
    }

    @Singleton
    @Provides
    fun providesProtoDataStore(@ApplicationContext context: Context): DataStore<UserDetail> {
        return DataStoreFactory.create(
            serializer = UserDetailSerializer,
            produceFile = {
                context.dataStoreFile(Constants.USER_PREFERENCE_PROTO)
            }
        )
    }



}