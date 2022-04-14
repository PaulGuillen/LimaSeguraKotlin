package devpaul.business.safetylima


import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import devpaul.business.safetylima.activities.category.CategoryActivity
import devpaul.business.safetylima.activities.register.RegisterActivity
import devpaul.business.safetylima.activities.register.TwitterActivity
import devpaul.business.safetylima.activities.resetpassword.ResetPasswordActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    var TAG = "MainActivity"

    //Vistas necesarias
    private var viewRegister: ImageButton? = null
    var edtEmail: EditText? = null
    var edtPassword: EditText? = null
    private var btnIniciarsesion: Button? = null
    var textContraseña : TextView? = null

    //Firebase
    private lateinit var auth: FirebaseAuth

    //Button Google
    var btnGoogle : Button ? = null
    private val RC_SIGN_IN = 777
    private var googleSignInClient: GoogleSignInClient? = null

    //Twitter
    var btnTwitter : Button ? = null

    //Progress Dialog

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        @Suppress("DEPRECATION")
        progressDialog = ProgressDialog(this)

        auth = Firebase.auth
        viewRegister = findViewById(R.id.imgb_next)
        edtEmail = findViewById(R.id.edt_correo)
        edtPassword = findViewById(R.id.edt_password)
        textContraseña = findViewById(R.id.olvidar_contraseña)
        btnIniciarsesion = findViewById(R.id.btn_ingresar)

        textContraseña?.setOnClickListener {
            goToResetPassword()
        }
        btnIniciarsesion?.setOnClickListener {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
            gotoRegisterActivity() }

        //Vista de Registro Activity
        viewRegister?.setOnClickListener {
            val i = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(i)
        }

        //Google SignIn
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_SIGN_IN))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btnGoogle = findViewById(R.id.btm_google)

        btnGoogle?.setOnClickListener {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
            signInWithGoogle()
        }


        //Twitter SignIn
        btnTwitter = findViewById(R.id.btn_twitter)
        btnTwitter?.setOnClickListener {
            val intent = Intent(this@MainActivity, TwitterActivity::class.java)
            startActivity(intent)
        }


    }

    private fun goToResetPassword(){
        val i = Intent(this, ResetPasswordActivity::class.java)
        startActivity(i)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    goToCategory()
                    progressDialog?.dismiss()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }

    @Suppress("DEPRECATION")
    private fun signInWithGoogle() {
        val signInIntent: Intent = googleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    @Suppress("DEPRECATION")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this@MainActivity, CategoryActivity::class.java))
            Toast.makeText(this, "Gracias por volver", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun gotoRegisterActivity() {
        val email = edtEmail?.text.toString()
        val password = edtPassword?.text.toString()

        if (isValidForm(email = email, password = password)) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "Usuario: $email\nContraseña: $password")
                        Toast.makeText(this@MainActivity, "Usuario autentificado.", Toast.LENGTH_SHORT).show()
                        goToCategory()
                        progressDialog?.dismiss()
                    } else {
                        Toast.makeText(this@MainActivity, "Usuario no encontrado.", Toast.LENGTH_SHORT).show()
                        Log.w(TAG, "Error: Usuario no encontrado", task.exception)
                    }
                }
        }


    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidForm(email: String, password: String): Boolean {

        if (email.isBlank()) {
            Toast.makeText(this@MainActivity, "Correo vacío", Toast.LENGTH_LONG).show()
            return false
        }

        if (password.isBlank()) {
            Toast.makeText(this@MainActivity, "Contraseña vacía", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!email.isEmailValid()) {
            Toast.makeText(this@MainActivity, "Email incorrecto", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun goToCategory() {
        val i = Intent(this, CategoryActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Eliminar el historial de pantallas
        startActivity(i)
    }

}