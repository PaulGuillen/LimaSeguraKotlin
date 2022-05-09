package devpaul.business.safetylima.routes

import devpaul.business.safetylima.entities.Data
import retrofit2.Call
import retrofit2.http.GET


interface RetrofitServiceNewsApart {

    @GET("v2/top-headlines?country=ar&apiKey=f206dd4aec1b46f38defa2ae5b0740e1")
    fun  getDataArgentina (): Call<Data>

}