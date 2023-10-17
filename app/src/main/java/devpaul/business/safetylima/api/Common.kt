package devpaul.business.safetylima.api

import devpaul.business.safetylima.data.routes.RetrofitService


object Common {
    private  val BASE_URL = "https://deperu.com/api/rest/"

    val retrofitService : RetrofitService
    get () = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}