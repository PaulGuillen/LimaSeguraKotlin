package devpaul.business.safetylima.domain.usecases

import android.content.Context
import devpaul.business.safetylima.data.models.entity.News
import devpaul.business.safetylima.data.repository.NewsRepository
import devpaul.business.safetylima.domain.custom_result.CustomResult

class NewsUseCase(
    var context: Context,
    private val newsRepository: NewsRepository
) {
    fun newsFromArgentina(): CustomResult<News> {
        val newsArgentina = newsRepository.getNewsFromArgentina()
        when (newsArgentina) {
            is CustomResult.OnSuccess -> {
                saveQuoteResponse(newsArgentina.data)
            }

            is CustomResult.OnError -> {
                saveQuoteResponse(News())
            }

        }
        return newsArgentina
    }

    private fun saveQuoteResponse(newsArgentina: News) {

    }

}