package devpaul.business.safetylima.data.routes

import devpaul.business.safetylima.entities.Dolar
import devpaul.business.safetylima.entities.News
import devpaul.business.safetylima.entities.UIT
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("noticias.json")
    fun  getNewsList (): Call<MutableList<News>>

    //@GET("cotizaciondolar.json")
    @GET("https://run.mocky.io/v3/b3bda72a-1798-410f-aa63-841d439cc31b")
    fun getDolarPeru () : Call<Dolar>

    @GET("uitperu.json")
    fun getUIT () : Call<UIT>


}