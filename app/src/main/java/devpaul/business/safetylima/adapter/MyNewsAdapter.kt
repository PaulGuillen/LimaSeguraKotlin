package devpaul.business.safetylima.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devpaul.business.safetylima.R
import cn.pedant.SweetAlert.SweetAlertDialog
import devpaul.business.safetylima.data.models.entity.NewsPeru

class MyNewsAdapter(private val context: Context, private val newsList: MutableList<NewsPeru>) :
    RecyclerView.Adapter<MyNewsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemVIew = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return MyViewHolder(itemVIew)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val news = newsList[position]

        Glide.with(context).load(newsList[position].imagen).into(holder.image)
        holder.txt_titulo.text = newsList[position].titulo

        if(newsList[position].resumen == null){
            holder.txt_resumen.text = "El resumen de esta noticia no esta disponible en este momento, lamentamos los incovenientes con los servidores."
        }else{
            holder.txt_resumen.text = newsList[position].resumen
        }

        holder.txt_palsclave.text = newsList[position].palsclave
        holder.txt_fecha.text = newsList[position].fecha

        holder.itemView.setOnClickListener {
            goToMainUrl(news)
        }
    }

    private fun goToMainUrl(news : NewsPeru){

        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Visitar página oficial?")
           .setCancelText("Cancelar")
            .setConfirmText("Si")
            .setConfirmClickListener {
                val url = news.url
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                context.startActivity(i)
                it.dismiss()
            }
            .showCancelButton(true)
            .setCancelClickListener { sDialog ->
                sDialog.cancel()
            }.show()

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var txt_titulo: TextView
        var txt_resumen: TextView
        var txt_palsclave: TextView
        var txt_fecha: TextView


        init {
            image = itemView.findViewById(R.id.image_news)
            txt_titulo = itemView.findViewById(R.id.titulo)
            txt_resumen = itemView.findViewById(R.id.resumen)
            txt_palsclave = itemView.findViewById(R.id.palsclave)
            txt_fecha = itemView.findViewById(R.id.fecha)
        }
    }
}