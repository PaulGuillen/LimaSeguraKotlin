package devpaul.business.safetylima.domain.interfaces.repository

import devpaul.business.safetylima.data.models.entity.NewsPeru
import devpaul.business.safetylima.domain.custom_result.CustomResult

interface NewsPeruRepositoryNetwork {
    fun newsFromPeru(): CustomResult<MutableList<NewsPeru>>
}