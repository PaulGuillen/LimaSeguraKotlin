package devpaul.business.safetylima.providers

import devpaul.business.safetylima.api.Common
import devpaul.business.safetylima.entities.Dolar
import devpaul.business.safetylima.entities.UIT
import devpaul.business.safetylima.data.routes.RetrofitService
import retrofit2.Call

class DolarUITProvider() {

    private var currencyDolarUIT : RetrofitService? = null

    init {
        val api = Common
        currencyDolarUIT = api.retrofitService
    }

    fun getCurrencyDolar() : Call<Dolar>?{
        return currencyDolarUIT?.getDolarPeru()
    }

    fun getCurrencyUIT() : Call<UIT>?{
        return currencyDolarUIT?.getUIT()
    }
}