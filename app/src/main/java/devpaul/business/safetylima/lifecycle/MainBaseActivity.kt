package devpaul.business.safetylima.lifecycle

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import devpaul.business.safetylima.BuildConfig
import devpaul.business.safetylima.domain.util.SharedPref

abstract class MainBaseActivity : BaseActivity() {

    private lateinit var sharedPref: SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SharedPref(this)
        validateVersionApp()
    }

    private fun validateVersionApp() {
        val currentVersionCode = BuildConfig.VERSION_CODE
        val latestVersionCode = 6 // El nuevo versionCode después de actualizar

        if (currentVersionCode < latestVersionCode && !sharedPref.getUpdateDialogShown()) {
            showUpdateDialog()
            sharedPref.setUpdateDialogShown(true)
        }
    }

    private fun showUpdateDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Actualización Disponible")
        builder.setMessage("Hay una nueva versión de la aplicación disponible. ¿Desea actualizar ahora?")
        builder.setPositiveButton("Actualizar") { _, _ ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=devpaul.business.safetylima"))
            startActivity(intent)
        }
        builder.setNegativeButton("Más tarde") { dialog, _ ->
            dialog.dismiss()
        }
        builder.setCancelable(false)
        builder.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearPreferences()
    }

    private fun clearPreferences() {
        val prefs = SharedPref(this)
        prefs.clearPreferences()
    }
}