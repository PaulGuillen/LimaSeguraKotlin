package devpaul.business.safetylima.domain.interfaces.repository

import devpaul.business.safetylima.data.models.entity.News
import devpaul.business.safetylima.domain.custom_result.CustomResult

interface NewsRepositoryNetwork {

    fun getNewsFromArgentina(): CustomResult<News>
    fun getNewsFromColombia(): CustomResult<News>
    fun getNewsFromCuba(): CustomResult<News>
    fun getNewsFromMexico(): CustomResult<News>
    fun getNewsFromVenezuela(): CustomResult<News>

}