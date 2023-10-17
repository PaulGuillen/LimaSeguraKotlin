package devpaul.business.safetylima.api

import devpaul.business.safetylima.data.routes.RetrofitService
import devpaul.business.safetylima.data.routes.RetrofitServiceNewsApart

class ApiRoutes {

    private val dePeruEndPoint = "https://deperu.com/api/rest/"
    private val newsEndPoint = "https://newsapi.org/"

    private val retrofit = RetrofitClient()

    fun getDePeruEndPoints(): RetrofitService {
        return retrofit.getClient(dePeruEndPoint).create(RetrofitService::class.java)
    }

    fun getEndpointsNews(): RetrofitServiceNewsApart {
        return retrofit.getClient(newsEndPoint).create(RetrofitServiceNewsApart::class.java)
    }
}