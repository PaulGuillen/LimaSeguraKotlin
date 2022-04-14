package devpaul.business.safetylima.adapter

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import devpaul.business.safetylima.R
import devpaul.business.safetylima.activities.category.sections.DetailedAllActivity
import devpaul.business.safetylima.entities.ViewAllSection


class ViewAllAdapter(val context: Activity,
                     var viewAS: ArrayList<ViewAllSection>) :
    RecyclerView.Adapter<ViewAllAdapter.ViewAllSectionViewHolder>() {

    private var TAG = "ViewAllSection"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllSectionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_view_all_item, parent, false)
        return ViewAllSectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewAllSectionViewHolder, position: Int) {

        val viewSection = viewAS[position]

        if (viewSection.type == "bombero"){

            val options: RequestOptions = RequestOptions()
                .error(R.drawable.bombero)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
            holder.imageView?.let { Glide.with(context).load(viewSection.image).apply(options).into(it) }

        } else if ( viewSection.type == "serenazgo"){

            val options: RequestOptions = RequestOptions()
                .error(R.drawable.serenazgo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
            holder.imageView?.let { Glide.with(context).load(viewSection.image).apply(options).into(it) }

        } else if ( viewSection.type == "policia"){

            val options: RequestOptions = RequestOptions()
                .error(R.drawable.policia)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
            holder.imageView?.let { Glide.with(context).load(viewSection.image).apply(options).into(it) }

        }

        //Falta implementar las demas vistas invisibles porque estoy captando por posciion no por modelo
        holder.textViewNameStation?.text = viewSection.name
        holder.textViewDistrictStation?.text = viewSection.distrito
        holder.textViewDirectionStation?.text = viewSection.direccion
        holder.textViewEmergenciaStation?.text = viewSection.emergencia
        holder.textViewInicialStation?.text = viewSection.inicial

        holder.imageView2?.let { Glide.with(context).load(viewSection.image2).into(it) }
        holder.imageView3?.let { Glide.with(context).load(viewSection.image3).into(it) }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailedAllActivity::class.java)
            intent.putExtra("detail", viewAS[position])
            context.startActivity(intent)
            Log.v(TAG, viewAS[position].toString())
        }

    }

    override fun getItemCount(): Int {
        return viewAS.size
    }

    class ViewAllSectionViewHolder (view: View): RecyclerView.ViewHolder(view) {


         var textViewNameStation: TextView? = null
         var textViewDistrictStation: TextView? =null
         var textViewDirectionStation: TextView? =null
         var textViewEmergenciaStation: TextView? =null
         var textViewInicialStation: TextView? =null


         var imageView: ImageView? =null
         var imageView2: ImageView? =null
         var imageView3: ImageView? =null

        init {

            imageView = view.findViewById(R.id.view_img)
            imageView2 = view.findViewById(R.id.view_img_dos)
            imageView3 = view.findViewById(R.id.view_img_tres)

            textViewNameStation = view.findViewById(R.id.name_station)
            textViewDistrictStation = view.findViewById(R.id.district_station)
            textViewDirectionStation = view.findViewById(R.id.direccion_station)
            textViewEmergenciaStation = view.findViewById(R.id.emergencias_station)
            textViewInicialStation = view.findViewById(R.id.inicial_station)

        }
    }


}