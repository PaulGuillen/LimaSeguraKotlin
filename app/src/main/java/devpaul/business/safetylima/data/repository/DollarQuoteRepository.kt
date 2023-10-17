package devpaul.business.safetylima.data.repository
import devpaul.business.safetylima.api.ApiRoutes
import devpaul.business.safetylima.data.models.entity.DollarQuote
import devpaul.business.safetylima.data.models.response.DollarQuoteResponse
import devpaul.business.safetylima.data.routes.RetrofitService
import devpaul.business.safetylima.domain.custom_result.CustomNotFoundError
import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.custom_result.HttpError
import devpaul.business.safetylima.domain.interfaces.repository.DollarQuoteRepositoryNetwork
import devpaul.business.safetylima.domain.mappers.DollarQuoteMapper

class DollarQuoteRepository : DollarQuoteRepositoryNetwork {

    private var apiConfig: RetrofitService? = null
    private var messageTimeOut = "Time Out"

    init {
        val api = ApiRoutes()
        apiConfig = api.getDePeruEndPoints()
    }

    override fun dollarQuote(): CustomResult<DollarQuote> {
        val serviceTitle = "Error en el MS validate EPP"

        try {

            val callApi = apiConfig?.dollarQuote()?.execute()
            val response: DollarQuoteResponse? = callApi?.body()

            return when (callApi?.isSuccessful) {
                true -> {
                    if (response != null)
                        CustomResult.OnSuccess(DollarQuoteMapper().map(response))
                    else {
                        CustomResult.OnError(CustomNotFoundError())
                    }

                }

                false -> {
                    CustomResult.OnError(
                        HttpError(
                            code = callApi.code(),
                            title = serviceTitle,
                            subtitle = callApi.message()
                        )
                    )
                }

                else -> {
                    CustomResult.OnError(
                        HttpError(
                            code = callApi?.code(),
                            title = serviceTitle,
                            subtitle = callApi?.message()
                        )
                    )
                }
            }

        } catch (ex: Exception) {
            return CustomResult.OnError(
                HttpError(
                    code = 408,
                    title = serviceTitle,
                    subtitle = messageTimeOut,
                )
            )
        }
    }

}