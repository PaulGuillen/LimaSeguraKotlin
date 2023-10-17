package devpaul.business.safetylima.domain.mappers

import devpaul.business.safetylima.data.models.entity.NewsPeru
import devpaul.business.safetylima.data.models.response.NewsPeruResponse

class NewsPeruMapper {
    fun mapList(newsPeruResponseList: MutableList<NewsPeruResponse>): MutableList<NewsPeru> {
        val newsPeruList = mutableListOf<NewsPeru>()

        for (response in newsPeruResponseList) {
            val newsPeru = NewsPeru(
                titulo = response.titulo,
                resumen = response.resumen,
                palsclave = response.palsclave,
                imagen = response.imagen,
                imagenpeque = response.imagenpeque,
                url = response.url,
                fecha = response.fecha
            )
            newsPeruList.add(newsPeru)
        }

        return newsPeruList
    }
}
