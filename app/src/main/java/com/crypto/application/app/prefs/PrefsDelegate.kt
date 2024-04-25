package com.crypto.application.app.prefs

import android.content.SharedPreferences
import java.io.IOException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class PrefsDelegate<T>(
    private val preferences: SharedPreferences,
    private val name: String,
    private val defValue: T
) : ReadWriteProperty<Any, T> {

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        with(preferences.edit()) {
            when (value) {
                is Boolean -> putBoolean(name, value)
                is Int -> putInt(name, value)
                is Float -> putFloat(name, value)
                is Long -> putLong(name, value)
                is String -> putString(name, value)
                else -> throw IOException(defValue.toString())
            }
            apply()
        }
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        with(preferences) {
            return when (defValue) {
                is Boolean -> (getBoolean(name, defValue) as? T) ?: defValue
                is Int -> (getInt(name, defValue) as? T) ?: defValue
                is Float -> (getFloat(name, defValue) as? T) ?: defValue
                is Long -> (getLong(name, defValue) as? T) ?: defValue
                is String -> (getString(name, defValue) as? T) ?: defValue
                else -> throw IOException(defValue.toString())
            }
        }
    }
}
