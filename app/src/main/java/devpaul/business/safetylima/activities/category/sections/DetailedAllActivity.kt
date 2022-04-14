package devpaul.business.safetylima.activities.category.sections

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import devpaul.business.safetylima.R
import devpaul.business.safetylima.entities.ViewAllSection

class DetailedAllActivity : AppCompatActivity() {

    var viewAllModel: ViewAllSection? = null

    var imageSlider: ImageSlider? = null

    //TextView
    var textName: TextView? = null
    var textDistrict: TextView? = null
    var textDirection: TextView? = null
    var textInicial: TextView? = null
    var textEmergencia: TextView? = null

    //Button

    var buttonInicial : Button? = null
    var buttonEmergencia : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_all)

        imageSlider = findViewById(R.id.imageslider)
        textName = findViewById(R.id.textview_name)
        textDistrict = findViewById(R.id.textview_district)
        textDirection = findViewById(R.id.textview_direction)
        textInicial = findViewById(R.id.textview_inicial)
        textEmergencia = findViewById(R.id.textview_emergencia)

        buttonInicial = findViewById(R.id.btn_inicial)
        buttonEmergencia = findViewById(R.id.btn_emergencia)

        val datos : Any? = intent.getSerializableExtra("detail")
        if (datos is ViewAllSection) {
            viewAllModel = datos
        }

        if (viewAllModel != null) {

            textName?.text = viewAllModel?.name
            textDistrict?.text = viewAllModel?.distrito
            textDirection?.text = viewAllModel?.direccion
            textInicial?.text = viewAllModel?.inicial
            textEmergencia?.text = viewAllModel?.emergencia

            val imageList = ArrayList<SlideModel>()
            imageList.add(SlideModel(viewAllModel?.image, ScaleTypes.CENTER_CROP))
            imageList.add(SlideModel(viewAllModel?.image2, ScaleTypes.CENTER_CROP))
            imageList.add(SlideModel(viewAllModel?.image3, ScaleTypes.CENTER_CROP))
            imageSlider?.setImageList(imageList)
        }


        buttonInicial?.setOnClickListener {
            val number: String = textInicial?.text.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null))
            startActivity(intent)

        }

        buttonEmergencia?.setOnClickListener {
            val number: String = textEmergencia?.text.toString()
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null))
            startActivity(intent)

        }

    }
}