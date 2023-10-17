package devpaul.business.safetylima.data.repository

import devpaul.business.safetylima.api.ApiRoutes
import devpaul.business.safetylima.data.models.entity.News
import devpaul.business.safetylima.data.models.response.NewsResponse
import devpaul.business.safetylima.data.routes.RetrofitServiceNewsApart
import devpaul.business.safetylima.domain.custom_result.CustomNotFoundError
import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.custom_result.HttpError
import devpaul.business.safetylima.domain.interfaces.repository.NewsRepositoryNetwork
import devpaul.business.safetylima.domain.mappers.NewsMapper

class NewsRepository : NewsRepositoryNetwork {

    private var apiConfig: RetrofitServiceNewsApart? = null
    private var messageTimeOut = "Time Out"

    init {
        val api = ApiRoutes()
        apiConfig = api.getEndpointsNews()
    }

    override fun getNewsFromArgentina(): CustomResult<News> {
        val serviceTitle = "Error en el MS Argentina News"

        try {

            val callApi = apiConfig?.getDataArgentina()?.execute()
            val response: NewsResponse? = callApi?.body()

            return when (callApi?.isSuccessful) {
                true -> {
                    if (response != null)
                        CustomResult.OnSuccess(NewsMapper().map(response))
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

    override fun getNewsFromMexico(): CustomResult<News> {
        TODO("Not yet implemented")
    }

}