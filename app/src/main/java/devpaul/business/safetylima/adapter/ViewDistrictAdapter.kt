package devpaul.business.safetylima.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
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
import devpaul.business.safetylima.activities.category.sections.ViewAllActivity
import devpaul.business.safetylima.entities.SectionDistrict
import devpaul.business.safetylima.entities.Station

class ViewDistrictAdapter(val context: Activity) :
    RecyclerView.Adapter<ViewDistrictAdapter.SectionViewHolder>() {

    var limaSection = emptyList<SectionDistrict>()

    @SuppressLint("NotifyDataSetChanged")
    internal fun setDataList(limaSection : List<SectionDistrict>){
        this.limaSection = limaSection
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_district, parent, false)
        return SectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {

        val category = limaSection[position] // CADA UNA DE LAS CATEGORIAS

        val options: RequestOptions = RequestOptions()
            .fitCenter()
            .error(R.drawable.government_services)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

        holder.textViewCategory.text = category.distrito
        Glide.with(context).load(category.image).apply(options).into(holder.imageViewCateogry)

        holder.itemView.setOnClickListener {  goToDistrict(category) }

    }
    private fun goToDistrict(category: SectionDistrict) {
        val i = Intent(context, ViewAllActivity::class.java)
        i.putExtra("distrito", category.distrito)
        context.startActivity(i)
    }

    override fun getItemCount(): Int {
        return limaSection.size
    }

    class SectionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewCategory: TextView
        val imageViewCateogry: ImageView

        init {
            textViewCategory = view.findViewById(R.id.home_district_textview)
            imageViewCateogry = view.findViewById(R.id.home_district_image)
        }
    }

}
