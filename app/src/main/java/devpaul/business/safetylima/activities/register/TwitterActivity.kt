package devpaul.business.safetylima.activities.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.OAuthProvider
import devpaul.business.safetylima.R
import devpaul.business.safetylima.activities.category.CategoryActivity

class TwitterActivity : AppCompatActivity() {

    var firebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter)

        firebaseAuth = FirebaseAuth.getInstance()

        val provider = OAuthProvider.newBuilder("twitter.com")

        provider.addCustomParameter("lang", "es")

        val pendingResultTask = firebaseAuth!!.pendingAuthResult
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                .addOnSuccessListener(
                    OnSuccessListener {
                        val intent = Intent(this@TwitterActivity, CategoryActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        Toast.makeText(this@TwitterActivity, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show()
                    })
                .addOnFailureListener {
                    // Handle failure.
                }
        } else {
            firebaseAuth!!
                .startActivityForSignInWithProvider( /* activity= */this, provider.build())
                .addOnSuccessListener {
                    val intent = Intent(this@TwitterActivity, CategoryActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    Toast.makeText(this@TwitterActivity, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    // Handle failure.
                }
        }

    }
}