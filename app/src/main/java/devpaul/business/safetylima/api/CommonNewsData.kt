package devpaul.business.safetylima.api

import devpaul.business.safetylima.api.apifornews.RetrofitClient
import devpaul.business.safetylima.routes.RetrofitServiceNewsApart

object CommonNewsData {

    private  val BASE_URL = "https://newsapi.org/"

    val retrofitService : RetrofitServiceNewsApart
    get () = RetrofitClient.getClient(BASE_URL).create(RetrofitServiceNewsApart::class.java)

}