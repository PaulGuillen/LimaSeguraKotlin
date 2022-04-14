package devpaul.business.safetylima.entities

class SectionDistrict(
    val distrito: String? = null,
    val image: Int? = null,

) {
    override fun toString(): String {
        return "LimaSectionDistritc(district=$distrito, image=$image)"
    }
}