package devpaul.business.safetylima.domain.usecases

import android.content.Context
import devpaul.business.safetylima.data.models.entity.NewsPeru
import devpaul.business.safetylima.data.repository.NewsPeruRepository
import devpaul.business.safetylima.domain.custom_result.CustomResult

class NewsPeruUseCase(
    var context: Context,
    private val newsPeruRepository: NewsPeruRepository
) {
    fun newsPeru(): CustomResult<MutableList<NewsPeru>> {
        val newsPeru = newsPeruRepository.newsFromPeru()
        when (newsPeru) {
            is CustomResult.OnSuccess -> {
                saveQuoteResponse(newsPeru.data)
            }

            is CustomResult.OnError -> {
                saveQuoteResponse(mutableListOf())
            }

        }
        return newsPeru
    }

    private fun saveQuoteResponse(newsPeru: MutableList<NewsPeru>) {

    }

}