package com.aredruss.warmaster.data

import android.content.Context
import com.aredruss.warmaster.data.model.RuleInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import timber.log.Timber

class InfoRepository(private val context: Context) {
    private val dataFile = context.resources.assets.open("dump.json")
    val ruleInfoStatListener = MutableStateFlow<RuleInfo?>(null)
    suspend fun readStats() = withContext(Dispatchers.IO) {
        try {
            val json = Json { ignoreUnknownKeys = true }
            val jsonData = json.decodeFromString(
                RuleInfo.serializer(),
                dataFile.bufferedReader().use { it.readText() }
            )
            dataFile.close()
            ruleInfoStatListener.emit(jsonData)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }
}