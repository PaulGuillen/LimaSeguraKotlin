package devpaul.business.safetylima.domain.usecases

import android.content.Context
import devpaul.business.safetylima.data.models.entity.UIT

import devpaul.business.safetylima.domain.custom_result.CustomResult
import devpaul.business.safetylima.domain.interfaces.repository.UITRepositoryNetwork

class UITUseCase(
    var context: Context,
    private val uitRepositoryNetwork: UITRepositoryNetwork
) {
    fun dataUIT(): CustomResult<UIT> {
        val dataUIT = uitRepositoryNetwork.dataUIT()
        when (dataUIT) {
            is CustomResult.OnSuccess -> {
                saveUITResponse(dataUIT.data)
            }

            is CustomResult.OnError -> {
                saveUITResponse(UIT())
            }

        }
        return dataUIT
    }

    private fun saveUITResponse(uitResponse: UIT) {

    }

}