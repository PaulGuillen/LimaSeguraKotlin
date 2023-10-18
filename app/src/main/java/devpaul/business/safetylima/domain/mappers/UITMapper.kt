package devpaul.business.safetylima.domain.mappers

import devpaul.business.safetylima.data.models.response.UITResponse
import devpaul.business.safetylima.data.models.entity.UIT

class UITMapper {
    fun map(uitResponse: UITResponse): UIT {
        return UIT(
            servicio = uitResponse.servicio,
            sitio = uitResponse.sitio,
            enlace = uitResponse.enlace,
            periodo = uitResponse.periodo,
            UIT = uitResponse.UIT,
        )
    }
}