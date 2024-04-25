package com.crypto.application.app.prefs

import android.content.SharedPreferences

class PreferencesStorage(
    prefs: SharedPreferences,
) {
    companion object {
        private const val BALANCE = "BALANCE"
        private const val CURRENCY = "CURRENCY"
        private const val LAST_UPDATE_TIME = "LAST_UPDATE_TIME"
    }

    var balance by PrefsDelegate(prefs, BALANCE, 0f)
    var currency by PrefsDelegate(prefs, CURRENCY, "")
    var lastUpdateTime by PrefsDelegate(prefs, LAST_UPDATE_TIME, 0L)

}
