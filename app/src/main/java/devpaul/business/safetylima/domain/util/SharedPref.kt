package devpaul.business.safetylima.domain.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPref(context: Context) {

    private var prefs: SharedPreferences? = null

    private val gson = Gson()

    init {
        prefs = context.getSharedPreferences("devpaul.business.safetylima", Context.MODE_PRIVATE)
    }

    fun saveData(key: String, value: String) {
        val prefsEditor: SharedPreferences.Editor? = prefs?.edit()
        prefsEditor?.putString(key, value)
        prefsEditor?.apply()
    }

    fun getData(key: String): String? {
        return prefs?.getString(key, "")
    }

    fun remove(key: String) {
        prefs?.edit()?.remove(key)?.apply()
    }

    fun clearPreferences() {
        val prefsEditor: SharedPreferences.Editor? = prefs?.edit()
        prefsEditor?.clear()
        prefsEditor?.apply()
    }

    fun setUpdateDialogShown(shown: Boolean) {
        val prefsEditor: SharedPreferences.Editor? = prefs?.edit()
        prefsEditor?.putBoolean("updateDialogShown", shown)
        prefsEditor?.apply()
    }

    fun getUpdateDialogShown(): Boolean {
        return prefs?.getBoolean("updateDialogShown", false) ?: false
    }
}
