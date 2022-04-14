package devpaul.business.safetylima.entities

import java.io.Serializable


class ViewAllSection(
    val name: String? = null,
    val image: String? = null,
    val image2: String? = null,
    val image3: String? = null,
    val type: String? = null,
    val distrito: String? = null,
    val direccion: String? = null,
    val emergencia: String? = null,
    val inicial: String? = null,
) : Serializable {

    override fun toString(): String {
        return "ViewAllSection(name=$name, image=$image, image2=$image2, image3=$image3, type=$type, distrito=$distrito, " +
                "direccion=$direccion, emergencia=$emergencia, inicial=$inicial)"
    }
}