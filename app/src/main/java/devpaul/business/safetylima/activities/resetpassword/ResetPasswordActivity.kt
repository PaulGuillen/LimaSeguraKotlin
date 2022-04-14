package devpaul.business.safetylima.activities.resetpassword

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tommasoberlose.progressdialog.ProgressDialogFragment
import devpaul.business.safetylima.MainActivity
import devpaul.business.safetylima.R

class ResetPasswordActivity : AppCompatActivity() {

    var backImg : ImageButton? = null

    private var edtCorreo : EditText? = null

    private var btnReenviar : Button? = null

    var email : String = ""

    private lateinit var auth: FirebaseAuth

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        auth = Firebase.auth
        backImg = findViewById(R.id.back_to_home)
        edtCorreo = findViewById(R.id.edt_correo)
        btnReenviar = findViewById(R.id.btn_reenviar)

        backImg?.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        btnReenviar?.setOnClickListener {
            email = edtCorreo?.text.toString()

            if (email.isNotEmpty()){
                ProgressDialogFragment.showProgressBar(this)
                resetPassword()
            }else{
                Toast.makeText(this,"Debe ingresar el email", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun resetPassword() {
        auth.setLanguageCode("es")
        auth.sendPasswordResetEmail(email) .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this,"Se envio un correo para restablecer contraseña",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"No se pudo enviar el correo de restablecer contraseña",Toast.LENGTH_LONG).show()
            }
            ProgressDialogFragment.hideProgressBar(this)
        }
    }
}