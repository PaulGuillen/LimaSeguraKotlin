package devpaul.business.safetylima.entities


data class Dolar(

    val servicio: String? = null,
    val sitio: String? = null,
    val enlace: String? = null,
    val Cotizacion: MutableList<Cotizacion>
)

data class Cotizacion(

    val Compra: Double? = null,
    val Venta: Double? = null
)





