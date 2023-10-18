package devpaul.business.safetylima.domain.interfaces.repository

import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.data.models.entity.UIT

interface UITRepositoryNetwork {
    fun dataUIT(): CustomResult<UIT>
}