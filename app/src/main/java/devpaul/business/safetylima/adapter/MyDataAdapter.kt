package devpaul.business.safetylima.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import devpaul.business.safetylima.R
import devpaul.business.safetylima.data.models.entity.ArticleNews

class MyDataAdapter(private val context: Context, private val articlesList: List<ArticleNews>) :
    RecyclerView.Adapter<MyDataAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemVIew = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false)
        return MyViewHolder(itemVIew)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val news = articlesList[position]

        if (articlesList[position].content == null) {
            holder.txt_resumen.text =
                "El resumen de esta noticia no esta disponible en este momento, lamentamos los incovenientes con los servidores."
        } else {
            holder.txt_resumen.text = articlesList[position].content
        }

        if (articlesList[position].description == null) {
            holder.txt_palsclave.text = "Las palabras claves de esta noticia no se encuentran disponibles en este momento."
        } else {
            holder.txt_palsclave.text = articlesList[position].description
        }

        if (articlesList[position].urlToImage == null) {
            Glide.with(context).load(R.drawable.news).into(holder.image);
        } else {
            Glide.with(context).load(articlesList[position].urlToImage).into(holder.image)
        }

        holder.txt_titulo.text = articlesList[position].title
        holder.txt_fecha.text = articlesList[position].publishedAt

        holder.itemView.setOnClickListener {
            goToMainUrl(news)
        }
    }

    private fun goToMainUrl(articles: ArticleNews) {

        SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE).setTitleText("Visitar página oficial?")
            .setCancelText("Cancelar")
            .setConfirmText("Si")
            .setConfirmClickListener {
                val url = articles.url
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
        return articlesList.size
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