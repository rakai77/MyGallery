package com.example.mygallery.data.source.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GalleryDatastore constructor(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("gal-datastore.pb")

    suspend fun <T> storeData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { pref ->
            pref[key] = value
        }
    }

    suspend fun clear() {
        context.dataStore.edit { pref ->
            pref.clear()
        }
    }

    val email: Flow<String>
        get() = context.dataStore.data.map { pref ->
        pref[EMAIL] ?: ""
    }


    companion object {
        val EMAIL = stringPreferencesKey("EMAIL")
    }
}