package devpaul.business.safetylima.activities.category.sections

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devpaul.business.safetylima.Constants
import devpaul.business.safetylima.R
import devpaul.business.safetylima.adapter.ViewAllAdapter
import devpaul.business.safetylima.entities.ViewAllSection
import java.util.*
import kotlin.collections.ArrayList

class ViewAllActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    var TAG = "ViewAllSection"
    private lateinit var recyclerViewAll: RecyclerView

    //ViewAllSection
    var viewAllList = ArrayList<ViewAllSection>()
    private var viewAllAdapter: ViewAllAdapter? = null

    @Suppress("DEPRECATION")
    var progressDialog: ProgressDialog? = null

    @SuppressLint("NotifyDataSetChanged", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_section)

        @Suppress("DEPRECATION")
        progressDialog = ProgressDialog(this)
        //desactivar rotacion pantalla
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        recyclerViewAll = findViewById(R.id.recyclerview_view_all)
        recyclerViewAll.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAllAdapter = ViewAllAdapter(this, viewAllList)
        recyclerViewAll.adapter = viewAllAdapter

        val type = intent.getStringExtra("type")
        Log.v(TAG, "Tipo = $type")

/*        getType()*/

        if (type != null && type.equals("bombero", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
            db.collection(Constants.PATH_ALL_SECTIONS).orderBy("distrito", Query.Direction.ASCENDING).whereEqualTo("type", "bombero")
                .addSnapshotListener { value, error ->
                    if (error != null) {
                        Log.v(TAG,"Error $error")
                        progressDialog?.dismiss()
                        return@addSnapshotListener
                    }
                    for (doc in value!!.documentChanges) {
                        val section = doc.document.toObject(ViewAllSection::class.java)
                        viewAllList.add(section)
                        viewAllAdapter?.notifyDataSetChanged()
                        progressDialog?.dismiss()
                    }
                }
        }

        if (type != null && type.equals("policia", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
            db.collection(Constants.PATH_ALL_SECTIONS).orderBy("distrito", Query.Direction.ASCENDING)
                .whereEqualTo("type", "policia")
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        Log.v(TAG,"Error $error")
                        progressDialog?.dismiss()
                        return@addSnapshotListener
                    }
                    for (doc in snapshots!!.documentChanges) {
                        val section = doc.document.toObject(ViewAllSection::class.java)
                        viewAllList.add(section)
                        viewAllAdapter?.notifyDataSetChanged()
                        progressDialog?.dismiss()

                    }
                }
        }

        if (type != null && type.equals("serenazgo", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
            db.collection(Constants.PATH_ALL_SECTIONS).orderBy("distrito", Query.Direction.ASCENDING)
                .whereEqualTo("type", "serenazgo")
                .addSnapshotListener { snapshots, error ->
                    if (error != null) {
                        Log.v(TAG,"Error $error")
                        progressDialog?.dismiss()
                        return@addSnapshotListener
                    }
                    for (doc in snapshots!!.documentChanges) {
                        val section = doc.document.toObject(ViewAllSection::class.java)
                        viewAllList.add(section)
                        viewAllAdapter?.notifyDataSetChanged()
                        progressDialog?.dismiss()
                    }
                }
        }

        val typeDistrict = intent.getStringExtra("distrito")

        if (typeDistrict != null && typeDistrict.equals("Villa el Salvador", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Villa el Salvador")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Santiago de Surco", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Santiago de Surco")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Villa María del Triunfo", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Villa María del Triunfo")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Ancon", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Ancon")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        } //Listo

        if (typeDistrict != null && typeDistrict.equals("Ate", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Ate")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        } // Listo

        if (typeDistrict != null && typeDistrict.equals("Barranco", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
               Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS).whereEqualTo("distrito", "Barranco")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        } // Listo

        if (typeDistrict != null && typeDistrict.equals("Breña", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Breña")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        } //Listo

        if (typeDistrict != null && typeDistrict.equals("Carabayllo", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Carabayllo")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        } // Listo

        if (typeDistrict != null && typeDistrict.equals("Chaclacayo", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Chaclacayo")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        } // Listo

        if (typeDistrict != null && typeDistrict.equals("Chorrillos", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Chorrillos")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Cieneguilla", ignoreCase = true)) {

                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Cieneguilla")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Comas", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Comas")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("El Agustino", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "El Agustino")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Independencia", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Independencia")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Jesus Maria", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Jesus Maria")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("La Molina", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "La Molina")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("La Victoria", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "La Molina")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Lima", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Lima")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Lince", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Lince")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Los Olivos", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Los Olivos")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Lurigancho", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Lurigancho")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Lurín", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Lurín")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Magdalena del Mar", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Magdalena del Mar")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Miraflores", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Miraflores")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Pachacamac", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Pachacamac")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Puente Piedra", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Puente Piedra")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Pueblo Libre", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Pueblo Libre")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Punta Negra", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Punta Negra")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Rimac", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Rimac")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("San Bartolo", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "San Bartolo")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("San Borja", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "San Borja")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("San Isidro", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "San Isidro")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("San Juan de Lurigancho", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "San Juan de Lurigancho")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("San Juan de Miraflores", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS).whereEqualTo("distrito", "San Juan de Miraflores")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("San Luis", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "San Luis")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("San Martin de Porres", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "San Martin de Porres")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("San Miguel", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "San Miguel")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Santa Anita", ignoreCase = true)) {
            progressDialog!!.show()
            progressDialog?.setContentView(R.layout.charge_dialog)
            Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Santa Anita")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }

        if (typeDistrict != null && typeDistrict.equals("Santa Rosa", ignoreCase = true)) {
                progressDialog!!.show()
                progressDialog?.setContentView(R.layout.charge_dialog)
                Objects.requireNonNull(progressDialog!!.window)?.setBackgroundDrawableResource(android.R.color.transparent)
                db.collection(Constants.PATH_ALL_SECTIONS)
                    .whereEqualTo("distrito", "Santa Rosa")
                    .addSnapshotListener { value, error ->
                        if (error != null) {
                            Log.v(TAG,"Error $error")
                            progressDialog?.dismiss()
                            return@addSnapshotListener
                        }
                        for (doc in value!!.documentChanges) {
                            val section = doc.document.toObject(ViewAllSection::class.java)
                            viewAllList.add(section)
                            viewAllAdapter?.notifyDataSetChanged()
                            progressDialog?.dismiss()
                        }
                    }
        }


    }

}