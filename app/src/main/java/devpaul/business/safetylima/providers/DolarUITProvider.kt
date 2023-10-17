package devpaul.business.safetylima.providers

import devpaul.business.safetylima.api.ApiRoutes
import devpaul.business.safetylima.data.models.response.DollarQuoteResponse
import devpaul.business.safetylima.entities.Dolar
import devpaul.business.safetylima.entities.UIT
import devpaul.business.safetylima.data.routes.RetrofitService
import retrofit2.Call

class DolarUITProvider() {

    private var currencyDolarUIT : RetrofitService? = null

    init {
        val api = ApiRoutes()
        currencyDolarUIT = api.getDePeruEndPoints()
    }


    fun getCurrencyUIT() : Call<UIT>?{
        return currencyDolarUIT?.getUIT()
    }
}