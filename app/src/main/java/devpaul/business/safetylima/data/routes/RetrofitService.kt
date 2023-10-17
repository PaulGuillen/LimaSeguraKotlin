package devpaul.business.safetylima.data.routes

import devpaul.business.safetylima.data.models.response.DollarQuoteResponse
import devpaul.business.safetylima.entities.Dolar
import devpaul.business.safetylima.entities.News
import devpaul.business.safetylima.entities.UIT
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("noticias.json")
    fun  getNewsList (): Call<MutableList<News>>

    @GET("cotizaciondolar.json")
    fun dollarQuote () : Call<DollarQuoteResponse>

    @GET("uitperu.json")
    fun getUIT () : Call<UIT>


}