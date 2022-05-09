package devpaul.business.safetylima.routes

import devpaul.business.safetylima.entities.Data
import retrofit2.Call
import retrofit2.http.GET


interface RetrofitServiceNewsApart {


    @GET("v2/top-headlines?country=ar&apiKey=Your_Key_From_NewsAPI")
    fun  getDataArgentina (): Call<Data>

    @GET("v2/top-headlines?country=co&apiKey=Your_Key_From_NewsAPI")
    fun  getDataColombia (): Call<Data>

    @GET("v2/top-headlines?country=cu&apiKey=Your_Key_From_NewsAPI")
    fun  getDataCuba (): Call<Data>

    @GET("v2/top-headlines?country=mx&apiKey=Your_Key_From_NewsAPI")
    fun  getDataMexico (): Call<Data>

    @GET("v2/top-headlines?country=ve&apiKey=Your_Key_From_NewsAPI")
    fun  getDataVenezuela (): Call<Data>

}