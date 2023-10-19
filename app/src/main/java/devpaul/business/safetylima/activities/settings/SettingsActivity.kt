package devpaul.business.safetylima.activities.settings

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import devpaul.business.safetylima.BuildConfig
import devpaul.business.safetylima.MainActivity
import devpaul.business.safetylima.R

class SettingsActivity : AppCompatActivity() {

    private var cTerms: ConstraintLayout? = null
    var cPolicy: ConstraintLayout? = null
    private var cShare: ConstraintLayout? = null
    private var logoemergencies: ImageView? = null
    private lateinit var auth: FirebaseAuth
    var versionText: TextView? = null
    var btnLogout: Button? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        auth = Firebase.auth

        cTerms = findViewById(R.id.clinearlayout_terms)
        cPolicy = findViewById(R.id.clinearlayout_policy)
        cShare = findViewById(R.id.clinearlayout_share)
        logoemergencies = findViewById(R.id.logo_emergency)
        versionText = findViewById(R.id.version)

        val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
        val versionName = packageInfo.versionName

        versionText?.text = "Version $versionName"


        btnLogout = findViewById(R.id.btn_salir)
        btnLogout?.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Gracias por visitarnos", Toast.LENGTH_LONG).show()
            val i = Intent(this, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Eliminar el historial de pantallas
            startActivity(i)
        }

        cShare?.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Lima Segura esta disponible en: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
            )
            sendIntent.type = "text/plain"
            startActivity(sendIntent)

        }

        cTerms?.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data =
                Uri.parse("https://paulguillen.github.io/Terminos-Condiciones/")
            startActivity(openURL)
        }

        cPolicy?.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data =
                Uri.parse("https://paulguillen.github.io/Politicas-Privacidad/")
            startActivity(openURL)
        }


    }
}