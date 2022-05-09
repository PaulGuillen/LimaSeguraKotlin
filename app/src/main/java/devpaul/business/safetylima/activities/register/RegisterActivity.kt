package devpaul.business.safetylima.activities.register

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tommasoberlose.progressdialog.ProgressDialogFragment
import devpaul.business.safetylima.Constants
import devpaul.business.safetylima.MainActivity
import devpaul.business.safetylima.R
import devpaul.business.safetylima.activities.category.CategoryActivity
import devpaul.business.safetylima.entities.User
import java.util.*

class RegisterActivity : AppCompatActivity() {

    var TAG = "RegisterActivity"

    //Vistas Necesarias
    var edtName: EditText? = null
    var edtLastname: EditText? = null
    var edtEmail: EditText? = null
    var edtPassword: EditText? = null
    var edtConfirmPassword: EditText? = null
    var btnRegistrar: Button? = null
    var viewMain: ImageButton? = null

    //Firebase extensiones
    private lateinit var auth: FirebaseAuth
    private val db = Firebase.firestore

    //Button Google
    var btnGoogle : Button? = null
    private val RC_SIGN_IN = 777
    private var googleSignInClient: GoogleSignInClient? = null

    //Twitter
    var btnTwitter : Button ? = null

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        @Suppress("DEPRECATION")
        progressDialog = ProgressDialog(this)

        auth = Firebase.auth
        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtLastname = findViewById(R.id.edt_lastname)
        edtPassword = findViewById(R.id.edt_password)
        edtConfirmPassword = findViewById(R.id.edt_confirm_password)
        btnRegistrar = findViewById(R.id.btn_registrar)
        viewMain = findViewById(R.id.imgb_back)

        btnRegistrar?.setOnClickListener { registerUser() }

        viewMain?.setOnClickListener {
            val i = Intent(this@RegisterActivity, MainActivity::class.java)
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
            ProgressDialogFragment.showProgressBar(this)
            signInWithGoogle()
        }


        //Twitter SignIn
        btnTwitter = findViewById(R.id.btn_twitter)
        btnTwitter?.setOnClickListener {
            val intent = Intent(this, TwitterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    goToCategory()
                    ProgressDialogFragment.hideProgressBar(this)
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

    private fun registerUser() {

        progressDialog!!.show()
        progressDialog?.setContentView(R.layout.charge_dialog)
        Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)

        val name = edtName?.text.toString()
        val lastname = edtLastname?.text.toString()
        val email = edtEmail?.text.toString()
        val password = edtPassword?.text.toString()
        val confirmPassword = edtConfirmPassword?.text.toString()


        if (isValidForm(
                name = name,
                lastname = lastname,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
        ) {

            val user = User(
                name = name,
                lastname = lastname,
                email = email,
                password = password
            )

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        auth.uid?.let { uid ->
                            db.collection(Constants.PATH_USERS)
                                .document(uid)
                                .set(user)
                                .addOnSuccessListener {
                                    ProgressDialogFragment.showProgressBar(this)
                                    Toast.makeText(baseContext, "Registro exitoso", Toast.LENGTH_SHORT).show()
                                    progressDialog?.dismiss()
                                    goToCategory()
                                    ProgressDialogFragment.hideProgressBar(this)
                                }
                                .addOnFailureListener { e ->
                                    Log.w(TAG, "Error adding document", e)
                                }

                        }

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Error en el registro.",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }

        }


    }

    fun String.isEmailValid(): Boolean {
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    private fun isValidForm(
        name: String,
        lastname: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {

        if (name.isBlank()) {
            Toast.makeText(this, "Debes ingresar el nombre", Toast.LENGTH_SHORT).show()
            return false
        }

        if (lastname.isBlank()) {
            Toast.makeText(this, "Debes ingresar el apellido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (email.isBlank()) {
            Toast.makeText(this, "Debes ingresar el email", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password.isBlank()) {
            Toast.makeText(this, "Debes ingresar el contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if (confirmPassword.isBlank()) {
            Toast.makeText(this, "Debes ingresar el la confirmacion de contraseña", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!email.isEmailValid()) {
            Toast.makeText(this, "El email no es valido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != confirmPassword) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
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