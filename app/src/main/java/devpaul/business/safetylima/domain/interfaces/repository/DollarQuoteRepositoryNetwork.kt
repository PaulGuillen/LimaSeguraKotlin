package devpaul.business.safetylima.domain.interfaces.repository

import devpaul.business.safetylima.data.models.entity.DollarQuote
import devpaul.business.safetylima.domain.custom_result.CustomResult

interface DollarQuoteRepositoryNetwork {

    fun dollarQuote(): CustomResult<DollarQuote>
}