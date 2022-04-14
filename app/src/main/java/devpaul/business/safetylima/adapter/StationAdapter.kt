package devpaul.business.safetylima.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import devpaul.business.safetylima.R
import devpaul.business.safetylima.activities.category.sections.ViewAllActivity
import devpaul.business.safetylima.entities.Station

class StationAdapter (val context : Activity):
    RecyclerView.Adapter<StationAdapter.ViewHolder>() {

    var TAG = "StationAdapter"
    var dataList = emptyList<Station>()

    @SuppressLint("NotifyDataSetChanged")
    internal fun setDataList(dataList : List<Station>){
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_station, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val category = dataList[position]
        category.image?.let { holder.imageViewCateogry.setImageResource(it) }
        holder.textViewCategory.text = category.name

        holder.itemView.setOnClickListener {
            goToSections(category)
        }
    }

    private fun goToSections(category: Station) {
        val i = Intent(context, ViewAllActivity::class.java)
        i.putExtra("type", category.type)
        context.startActivity(i)
        Log.v(TAG, "Data " + category.image + "\n" +  category.name + "\n" + category.type)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

        val textViewCategory: TextView
        val imageViewCateogry: ImageView

        init {
            textViewCategory = view.findViewById(R.id.textview_category)
            imageViewCateogry = view.findViewById(R.id.imageview_category)
        }

    }

}