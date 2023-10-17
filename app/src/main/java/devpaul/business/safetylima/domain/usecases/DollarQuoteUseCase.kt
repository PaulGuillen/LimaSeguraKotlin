package devpaul.business.safetylima.domain.usecases

import android.content.Context
import devpaul.business.safetylima.data.models.entity.DollarQuote
import devpaul.business.safetylima.data.repository.DollarQuoteRepository
import devpaul.business.safetylima.domain.custom_result.CustomResult

class DollarQuoteUseCase(
    var context: Context,
    private val dollarQuoteRepository: DollarQuoteRepository
) {
    fun dollarQuote(): CustomResult<DollarQuote> {
        val dollarQuote = dollarQuoteRepository.dollarQuote()
        when (dollarQuote) {
            is CustomResult.OnSuccess -> {
                saveQuoteResponse(dollarQuote.data)
            }

            is CustomResult.OnError -> {
                saveQuoteResponse(DollarQuote())
            }

        }
        return dollarQuote
    }

    private fun saveQuoteResponse(dollarQuote: DollarQuote) {

    }

}