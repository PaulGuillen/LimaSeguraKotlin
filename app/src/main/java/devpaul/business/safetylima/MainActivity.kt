package devpaul.business.safetylima

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.TextUtils
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
import devpaul.business.safetylima.databinding.ActivityMainBinding
import devpaul.business.safetylima.lifecycle.MainBaseActivity
import java.util.*

class MainActivity : MainBaseActivity() {

    private lateinit var auth: FirebaseAuth

    private val RC_SIGN_IN = 777
    private var googleSignInClient: GoogleSignInClient? = null
    var btnTwitter: Button? = null

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.forgotPassword.setOnClickListener {
            goToResetPassword()
        }
        binding.btnLogin.setOnClickListener {
            goToHomeFragment()
        }

        binding.iBRegister.setOnClickListener {
            val i = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(i)
        }
        //Google SignIn
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_SIGN_IN))
            .requestEmail().build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        binding.btnGoogle.setOnClickListener {
            signInWithGoogle()
        }

        //Twitter SignIn
        btnTwitter = findViewById(R.id.btn_twitter)
        btnTwitter?.setOnClickListener {
            val intent = Intent(this@MainActivity, TwitterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun goToResetPassword() {
        val i = Intent(this, ResetPasswordActivity::class.java)
        startActivity(i)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        showLoading()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                hideLoading()
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                goToCategory()

            } else {
                hideLoading()
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
                firebaseAuthWithGoogle(account.idToken!!)
                hideLoading()
            } catch (e: ApiException) {
                hideLoading()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            startActivity(Intent(this@MainActivity, CategoryActivity::class.java))
            finish()
        }
    }

    private fun goToHomeFragment() {
        val email = binding.eTEmail.text.toString()
        val password = binding.eTPassword.text.toString()

        if (isValidForm(email = email, password = password)) {
            showLoading()
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    hideLoading()
                    goToCategory()

                } else {
                    hideLoading()
                    Toast.makeText(this@MainActivity, "Usuario no encontrado.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun String.isEmailValid(): Boolean {
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