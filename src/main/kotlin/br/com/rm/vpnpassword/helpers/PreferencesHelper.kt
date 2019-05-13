package br.com.rm.vpnpassword.helpers

import java.util.prefs.Preferences

class PreferencesHelper {

    private val USER_SECRET_KEY = "user_secret_key"
    private val USER_PASSWORD = "user_password"

    private var preferences: Preferences? = null

    init {
        preferences = Preferences.userRoot().node("vpnPassword")
    }

    fun saveSecurityKey(value: String) {
        preferences?.put(USER_SECRET_KEY, value)
    }

    fun loadSecurityKey(): String? {
        val toString = preferences?.get(USER_SECRET_KEY, "")
        return toString
    }

    fun savePassword(value: String) {
        preferences?.put(USER_PASSWORD, value)
    }

    fun loadPassword(): String {
        return preferences?.get(USER_PASSWORD, "").toString()
    }
}