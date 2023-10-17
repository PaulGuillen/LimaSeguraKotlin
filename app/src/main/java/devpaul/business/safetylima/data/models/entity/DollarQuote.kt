package devpaul.business.safetylima.data.models.entity
data class DollarQuote(
    var servicio: String? = null,
    var sitio: String? = null,
    var enlace: String? = null,
    var Cotizacion: MutableList<Cotizacion> = mutableListOf(),
    var importante: String? = null,
    var dolaresxEuro: String? = null,
    var fecha: String? = null
)

data class Cotizacion(
    var compra: Double? = null,
    var venta: Double? = null
)

