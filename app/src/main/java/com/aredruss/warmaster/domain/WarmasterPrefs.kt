package com.aredruss.warmaster.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class WarmasterPrefs(appContext: Context) {

    companion object {
        private const val prefsName = "warmasterPrefs"
        private const val DATA_VERSION_KEY = "data_version"
    }

    private val prefs: SharedPreferences =
        appContext.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    var currentDataVersion: Long by prefs.long(0L)

}


private fun SharedPreferences.long(
    defaultValue: Long,
    key: (KProperty<*>) -> String = KProperty<*>::name
) = object : ReadWriteProperty<Any, Long> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Long =
        getLong(key(property), defaultValue)

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) =
        edit { putLong(key(property), value) }
}