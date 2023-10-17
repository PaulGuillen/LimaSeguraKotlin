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
                saveResponse(newsArgentina.data)
            }

            is CustomResult.OnError -> {
                saveResponse(News())
            }

        }
        return newsArgentina
    }

    fun newsFromColombia(): CustomResult<News> {
        val newsColombia = newsRepository.getNewsFromColombia()
        when (newsColombia) {
            is CustomResult.OnSuccess -> {
                saveResponse(newsColombia.data)
            }

            is CustomResult.OnError -> {
                saveResponse(News())
            }

        }
        return newsColombia
    }

    fun newsFromCuba(): CustomResult<News> {
        val newsCuba = newsRepository.getNewsFromCuba()
        when (newsCuba) {
            is CustomResult.OnSuccess -> {
                saveResponse(newsCuba.data)
            }

            is CustomResult.OnError -> {
                saveResponse(News())
            }

        }
        return newsCuba
    }

    fun newsFromMexico(): CustomResult<News> {
        val newsMexico = newsRepository.getNewsFromMexico()
        when (newsMexico) {
            is CustomResult.OnSuccess -> {
                saveResponse(newsMexico.data)
            }

            is CustomResult.OnError -> {
                saveResponse(News())
            }

        }
        return newsMexico
    }

    fun newsFromVenezuela(): CustomResult<News> {
        val newsVenezuela = newsRepository.getNewsFromVenezuela()
        when (newsVenezuela) {
            is CustomResult.OnSuccess -> {
                saveResponse(newsVenezuela.data)
            }

            is CustomResult.OnError -> {
                saveResponse(News())
            }

        }
        return newsVenezuela
    }

    private fun saveResponse(newsArgentina: News) {

    }

}