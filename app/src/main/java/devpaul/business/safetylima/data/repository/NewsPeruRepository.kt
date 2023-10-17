package devpaul.business.safetylima.data.repository

import devpaul.business.safetylima.api.ApiRoutes
import devpaul.business.safetylima.data.models.entity.NewsPeru
import devpaul.business.safetylima.data.models.response.NewsPeruResponse
import devpaul.business.safetylima.data.routes.RetrofitService
import devpaul.business.safetylima.domain.custom_result.CustomNotFoundError
import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.custom_result.HttpError
import devpaul.business.safetylima.domain.interfaces.repository.NewsPeruRepositoryNetwork
import devpaul.business.safetylima.domain.mappers.NewsPeruMapper

class NewsPeruRepository : NewsPeruRepositoryNetwork {

    private var apiConfig: RetrofitService? = null
    private var messageTimeOut = "Time Out"

    init {
        val api = ApiRoutes()
        apiConfig = api.getDePeruEndPoints()
    }

    override fun newsFromPeru(): CustomResult<MutableList<NewsPeru>> {
        val serviceTitle = "Error en el MS News Peru"

        try {

            val callApi = apiConfig?.newsFromPeru()?.execute()
            val response: MutableList<NewsPeruResponse>? = callApi?.body()

            return when (callApi?.isSuccessful) {
                true -> {
                    if (response != null)
                        CustomResult.OnSuccess(NewsPeruMapper().mapList(response))
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