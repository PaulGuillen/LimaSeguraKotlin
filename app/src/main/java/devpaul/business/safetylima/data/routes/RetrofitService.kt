package devpaul.business.safetylima.data.routes

import devpaul.business.safetylima.data.models.response.DollarQuoteResponse
import devpaul.business.safetylima.data.models.response.NewsPeruResponse
import devpaul.business.safetylima.data.models.response.UITResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("noticias.json")
    fun newsFromPeru(): Call<MutableList<NewsPeruResponse>>

    @GET("cotizaciondolar.json")
    fun dollarQuote(): Call<DollarQuoteResponse>

    @GET("uitperu.json")
    fun dataUIT(): Call<UITResponse>

}