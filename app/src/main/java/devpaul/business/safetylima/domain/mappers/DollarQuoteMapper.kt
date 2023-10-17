package devpaul.business.safetylima.domain.mappers

import devpaul.business.safetylima.data.models.entity.Cotizacion
import devpaul.business.safetylima.data.models.entity.DollarQuote
import devpaul.business.safetylima.data.models.response.CotizacionItem
import devpaul.business.safetylima.data.models.response.DollarQuoteResponse

class DollarQuoteMapper {
    fun map(dollarQuoteResponse: DollarQuoteResponse): DollarQuote {
        return DollarQuote(
            servicio = dollarQuoteResponse.servicio,
            sitio = dollarQuoteResponse.sitio,
            enlace = dollarQuoteResponse.enlace,
            Cotizacion = dollarQuoteResponse.cotizacion?.map { mapQuote(it) }?.toMutableList() ?: mutableListOf(),
            importante = dollarQuoteResponse.importante,
            dolaresxEuro = dollarQuoteResponse.dolaresxEuro,
            fecha = dollarQuoteResponse.fecha
        )
    }

    private fun mapQuote(quote: CotizacionItem): Cotizacion {
        return Cotizacion(
            compra = quote.compra,
            venta = quote.venta
        )
    }
}
