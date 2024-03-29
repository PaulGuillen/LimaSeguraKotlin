package devpaul.business.safetylima.data.routes

import devpaul.business.safetylima.data.models.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServiceNewsApart {

    @GET("v2/top-headlines?country=ar&apiKey=f206dd4aec1b46f38defa2ae5b0740e1")
    fun  getDataArgentina (): Call<NewsResponse>

    @GET("v2/top-headlines?country=co&apiKey=f206dd4aec1b46f38defa2ae5b0740e1")
    fun  getDataColombia (): Call<NewsResponse>

    @GET("v2/top-headlines?country=cu&apiKey=f206dd4aec1b46f38defa2ae5b0740e1")
    fun  getDataCuba (): Call<NewsResponse>

    @GET("v2/top-headlines?country=mx&apiKey=f206dd4aec1b46f38defa2ae5b0740e1")
    fun  getDataMexico (): Call<NewsResponse>

    @GET("v2/top-headlines?country=ve&apiKey=f206dd4aec1b46f38defa2ae5b0740e1")
    fun  getDataVenezuela (): Call<NewsResponse>

}