package com.jim.multipos.utils

import android.content.Context
import android.preference.PreferenceManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefsManager @Inject constructor(val context: Context?) {

    inline fun <reified T> getValue(key: String, defaultValue: T): T {
        val manager = PreferenceManager.getDefaultSharedPreferences(this.context)
        when(T::class.java) {
            Int::class.java -> {
                return manager.getInt(key, defaultValue as Int) as T
            }
            String::class.java -> {
                return manager.getString(key, defaultValue as String) as T
            }
            Boolean::class.java -> {
                return manager.getBoolean(key, defaultValue as Boolean) as T
            }
            Long::class.java -> {
                return manager.getLong(key, defaultValue as Long) as T
            }
            Float::class.java -> {
                return manager.getFloat(key, defaultValue as Float) as T
            }
            else -> {
                throw Exception("Manager does not support this type: ${T::class.java.simpleName}")
            }
        }
    }

    inline fun <reified T> putValue(key: String, value: T) {
        val manager = PreferenceManager.getDefaultSharedPreferences(this.context)
        when(T::class.java) {
            Integer::class.java -> {
                manager.edit().putInt(key, value as Int).apply()
            }
            String::class.java -> {
                manager.edit().putString(key, value as String).apply()
            }
            Boolean::class.java -> {
                manager.edit().putBoolean(key, value as Boolean).apply()
            }
            Long::class.java -> {
                manager.edit().putLong(key, value as Long).apply()
            }
            Float::class.java -> {
                manager.edit().putFloat(key, value as Float).apply()
            }
            else -> {
                throw Exception("Manager does not support this type: ${T::class.java.simpleName}")
            }
        }
    }

}

